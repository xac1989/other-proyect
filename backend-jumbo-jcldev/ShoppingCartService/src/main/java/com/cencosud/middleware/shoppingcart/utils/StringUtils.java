package com.cencosud.middleware.shoppingcart.utils;

public class StringUtils {

	public static String getStringValue(Object val){
		return val!=null&&"null".equals(val.toString())?"":val.toString();
	}

	public static boolean isNull(String val){
		return (val==null||"null".equals(val))?true:false;
	}
	
}
