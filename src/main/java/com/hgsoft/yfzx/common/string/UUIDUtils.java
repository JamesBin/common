package com.hgsoft.yfzx.common.string;

import java.util.UUID;

/**
 * 功能描述：返回一个UUID字符串。
 */
public class UUIDUtils {

    /**
     * 功能描述：返回一个UUID字符串。
     * @return
     */
	public static String getID()
	{
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getID());
	}
	
}
