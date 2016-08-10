package com.herman.summary.common.method.formatprocess;

/**
 * Created by liuchangfa on 2016/8/5.
 */
public class FormatConversion
{

	/**
	 * 将long转化为16位byte数组
	 */
	public static byte[] longToByte(long l, long n)
	{
		long temp1 = l;
		long temp2 = n;
		byte[] b = new byte[16];
		for (int i = 0; i < 8; i++)
		{
			b[i] = new Long(temp1 & 0xff).byteValue();
			temp1 = temp1 >> 8;
		}
		for (int i = 8; i < 16; i++)
		{
			b[i] = new Long(temp2 & 0xff).byteValue();
			temp2 = temp2 >> 8;
		}

		return b;
	}


	/**
	 * 将byte转化为两位long
	 */
	public static long[] byteToLong(byte[] b)
	{
		long[] l = new long[2];
		long l0 = b[0] & 0xff;
		long l1 = b[1] & 0xff;
		long l2 = b[2] & 0xff;
		long l3 = b[3] & 0xff;
		long l4 = b[4] & 0xff;
		long l5 = b[5] & 0xff;
		long l6 = b[6] & 0xff;
		long l7 = b[7] & 0xff;
		l1 <<= 8;
		l2 <<= 16;
		l3 <<= 24;
		l4 <<= 8 * 4;
		l5 <<= 8 * 5;
		l6 <<= 8 * 6;
		l7 <<= 8 * 7;
		l[0] = l0 | l1 | l2 | l3 | l4 | l5 | l6 | l7;
		long l8 = b[8] & 0xff;
		long l9 = b[9] & 0xff;
		long l10 = b[10] & 0xff;
		long l11 = b[11] & 0xff;
		long l12 = b[12] & 0xff;
		long l13 = b[13] & 0xff;
		long l14 = b[14] & 0xff;
		long l15 = b[15] & 0xff;
		l9 <<= 8;
		l10 <<= 16;
		l11 <<= 8 * 3;
		l12 <<= 8 * 4;
		l13 <<= 8 * 5;
		l14 <<= 8 * 6;
		l15 <<= 8 * 7;
		l[1] = l8 | l9 | l10 | l11 | l12 | l13 | l14 | l15;
		return l;
	}


	/**
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序,既小端模式。
	 *
	 * @param value 要转换的int值
	 * @return byte数组
	 */
	public static byte[] intToBytes(int value)
	{
		byte[] src = new byte[4];
		src[3] = (byte) ((value >> 24) & 0xFF);
		src[2] = (byte) ((value >> 16) & 0xFF);
		src[1] = (byte) ((value >> 8) & 0xFF);
		src[0] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * 将byte数组转换成整型
	 */
	public static int byteToint(byte[] res)
	{
		return (res[0] & 0xff) | ((res[1] << 8) & 0xff00) | (res[2] << 24 >>> 8) | (res[3] << 24);
	}

	/**
	 * 将byte转化为16进制
	 */
	public static String byteTohex(byte[] buffer)
	{
		String h = "";

		for (byte aBuffer : buffer)
		{
			String temp = Integer.toHexString(aBuffer & 0xFF);
			if (temp.length() == 1)
			{
				temp = "0" + temp;
			}
			h = h + " " + temp;
		}
		return h;
	}

}
