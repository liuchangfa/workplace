/**
 * Created by liuchangfa on 2016/8/5.
 */

package com.herman.summary.action.method.nvram;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

import com.herman.summary.common.method.formatprocess.FormatConversion;
import com.mediatek.connectivity.NvRAMAgent;

import java.io.UnsupportedEncodingException;

public class NVRAM
{
	public static final String TAG                               = "herman";
	//最多存储秘钥组为24
	public static       int    SECRET_KEYS_MAX                   = 24;
	//没组秘钥长度(字节)
	public static       int    SECRET_KEY_LENGTH                 = 32;
	//NVRAM ProdectInfo总大小(字节)
	public static       int    NVRAM_PRODECT_INFO_SPACE          = 1024;
	//NVRAM board大小(字节)
	public static       int    NVRAM_BARCODE_SPACE               = 64;
	//NVRAM 存储IMEI空间大小(字节)
	public static       int    NVRAM_IMEI_SPACE                  = 40;
	//NVRAM Reserved空间大小(字节)
	public static       int    NVRAM_RESERVED_SPACE              = NVRAM_PRODECT_INFO_SPACE - NVRAM_BARCODE_SPACE - NVRAM_IMEI_SPACE;
	//NVRAM预留空间字节数
	public static       int    NVRAM_RESREVED_LAST_SPACE         = NVRAM_RESERVED_SPACE - SECRET_KEYS_MAX * SECRET_KEY_LENGTH;
	//NV读写id
	public static       int    AP_CFG_REEB_PRODUCT_INFO_LID      = 59;
	//NV设备读写路径
	public static final String AP_CFG_REEB_PRODUCT_INFO_LID_FILE = "/data/nvram/APCFG/APRDEB/PRODUCT_INFO";
	public static final String KEY_PAY1                          = "abcdefgqwertyuioplkjnmwwerfvdsca";

	/***********************************************************************************************
	 * 功能：nvram读写
	 * 作者：liuchangfa
	 **********************************************************************************************/
	//Write NVRam
	public static synchronized void writeDataToNvram(int file_lid, byte[] bufferWrite)
	{
		IBinder binder = ServiceManager.getService("NvRAMAgent");
		NvRAMAgent agent = NvRAMAgent.Stub.asInterface(binder);
		try
		{
			//根据id来写nv
			int flag = agent.writeFile(file_lid, bufferWrite);
			//根据文件路径来写nv
			//			int flag = agent.writeFileByName(AP_CFG_REEB_PRODUCT_INFO_LID_FILE, bufferWrite);
			Log.v(TAG, "write success flag=" + flag);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
			Log.v(TAG, "write failed" + e);
		}
	}

	//Read NvRAM
	public static synchronized byte[] readDataFromNvram(int file_lid)
	{
		IBinder binder = ServiceManager.getService("NvRAMAgent");
		NvRAMAgent agent = NvRAMAgent.Stub.asInterface(binder);
		byte buffRead[] = new byte[NVRAM_PRODECT_INFO_SPACE];
		try
		{
			//根据id来读数据
			buffRead = agent.readFile(file_lid);// read buffer from nvram
			//根据文件路径读
			//			buffRead = agent.readFileByName(AP_CFG_REEB_PRODUCT_INFO_LID_FILE);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}

		return buffRead;
	}

	/**
	 * 保存秘钥
	 * @param id   : 保存的秘钥id(0<= id <24)
	 * @param vaule: 需要保存的秘钥,长度必须是32个字节
	 */
	public static boolean saveSecretKeys(int id, String vaule) throws IllegalArgumentException
	{
		//检测传入参数是否正确
		if (id < 0 || id > SECRET_KEYS_MAX || vaule.length() != SECRET_KEY_LENGTH)
		{
			throw new IllegalArgumentException();
		}
		//保存秘钥的起始位置
		int startSaveId = NVRAM_IMEI_SPACE + NVRAM_BARCODE_SPACE + id * SECRET_KEY_LENGTH;
		byte bufferWrite[] = readDataFromNvram(AP_CFG_REEB_PRODUCT_INFO_LID);
		byte[] keyBytes = vaule.getBytes();
		for (byte b : keyBytes)
		{
			bufferWrite[startSaveId++] = b;
		}
		writeDataToNvram(AP_CFG_REEB_PRODUCT_INFO_LID, bufferWrite);

		return readSecretKeys(id, null).equals(vaule);
	}

	/**
	 * 读取秘钥
	 * @param id      : 需要读取的秘钥id(0<= id <24)
	 * @param defVaule: 读取不到秘钥取默认值
	 */
	public static String readSecretKeys(int id, String defVaule) throws IllegalArgumentException
	{
		//检测传入参数是否正确
		if (id < 0 || id > SECRET_KEYS_MAX)
		{
			throw new IllegalArgumentException();
		}
		//读取秘钥的起始位置
		int startReadId = NVRAM_IMEI_SPACE + NVRAM_BARCODE_SPACE + id * SECRET_KEY_LENGTH;
		byte bufferWrite[] = readDataFromNvram(AP_CFG_REEB_PRODUCT_INFO_LID);

		//测试代码
		String mNvram_HEX = FormatConversion.byteTohex(bufferWrite);
		Log.d(TAG, "读取nvram内容-->:" + mNvram_HEX);

		byte secretKey[] = new byte[32];
		System.arraycopy(bufferWrite, startReadId, secretKey, 0, 32);
		try
		{
			return new String(secretKey, "GB2312");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return defVaule;
		}
	}

	/**
	 * 删除保存的秘钥
	 * @param id   : 需要删除的秘钥id(0<= id <24)
	 */
	public static void deleteSecretKeys(int id) throws IllegalArgumentException
	{
		//检测传入参数是否正确
		if (id < 0 || id > SECRET_KEYS_MAX)
		{
			throw new IllegalArgumentException();
		}
		int startSaveId = NVRAM_IMEI_SPACE + NVRAM_BARCODE_SPACE + id * SECRET_KEY_LENGTH;
		byte bufferWrite[] = readDataFromNvram(AP_CFG_REEB_PRODUCT_INFO_LID);
		byte[] keyBytes;
		keyBytes = new byte[32];
		for (byte b : keyBytes)
		{
			bufferWrite[startSaveId++] = b;
		}
		writeDataToNvram(AP_CFG_REEB_PRODUCT_INFO_LID, bufferWrite);
	}

	/**
	 * 格式化NVRAM，清除所有保存的秘钥
	 */
	public static void formatSecretKeys()
	{
		byte bufferWrite[] = new byte[NVRAM_PRODECT_INFO_SPACE];
		writeDataToNvram(AP_CFG_REEB_PRODUCT_INFO_LID, bufferWrite);
	}

}
