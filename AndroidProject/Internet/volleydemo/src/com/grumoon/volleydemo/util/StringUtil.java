package com.grumoon.volleydemo.util;

public class StringUtil {
	/**
	 * 判断字符是否为空
	 * @param str被判断字符
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||str.trim().length()==0){
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	/**
	 * 自动补全http协议
	 * @param 
	 * @return
	 */
	public static String preUrl(String url){
		if(url==null){
			return null;
		}
		if(url.startsWith("http://")||url.startsWith("https://")){
			return url;
		}
		else{
			return "http://"+url;
		}
	}

}
