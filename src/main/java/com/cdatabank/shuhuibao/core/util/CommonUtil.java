package com.cdatabank.shuhuibao.core.util;

import java.util.Set;

import javax.servlet.http.HttpSession;

import com.cdatabank.shuhuibao.core.listener.SessionListener;

public class CommonUtil {

	/**
	 * 
	 * @param userId
	 * @return true则表示该用户已经登陆 
	 */
	public static boolean isLogonUser(String userId) {
		Set<HttpSession> keys = SessionListener.loginUser.keySet();
		for (HttpSession key : keys) {
			if (SessionListener.loginUser.get(key).equals(userId)) {
				key.invalidate();//清除第一次会话
				return true;
			}
		}
		return false;
	}

}
