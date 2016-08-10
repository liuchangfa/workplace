package com.herman.summary.action.method.nvram;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

import com.herman.summary.common.method.formatprocess.FormatConversion;
import com.mediatek.connectivity.NvRAMAgent;

/**
 * Created by Administrator on 2016/8/5.
 */
public class NVRAM
{
	public static final String TAG            = "herman";
	//NV读写id
	public static       int    NVRAM_FILE_LID = 71;

	/***********************************************************************************************
	 * 功能：nvram读写
	 * 作者：liuchangfa
	 **********************************************************************************************/

	public static synchronized void WriteData(int file_lid)
	{//写入NVRam
		IBinder binder = ServiceManager.getService("NvRAMAgent");
		NvRAMAgent agent = NvRAMAgent.Stub.asInterface(binder);
		byte[] buffWrie = FormatConversion.longToByte(23, 44);
		try
		{
			int flag = agent.writeFile(file_lid, buffWrie);
			Log.v(TAG, "write success flag=" + flag);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
			Log.v(TAG, "write failed" + e);
		}
	}

	//Read NvRAM
	public static synchronized void ReadData(int file_lid)
	{
		IBinder binder = ServiceManager.getService("NvRAMAgent");
		NvRAMAgent agent = NvRAMAgent.Stub.asInterface(binder);
		byte buffRead[] = new byte[16];
		try
		{
			buffRead = agent.readFile(file_lid);// read buffer from nvram
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		//输出结果
		Log.e(TAG, "buffs = " + FormatConversion.byteTohex(buffRead));
		long buffLong[] = FormatConversion.byteToLong(buffRead);
		Log.e(TAG, "buffLong[0] = " + buffLong[0] + "\n" + "buffLong[1] = " + buffLong[1]);
	}
}
