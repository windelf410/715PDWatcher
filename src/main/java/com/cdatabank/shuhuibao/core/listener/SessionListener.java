package com.cdatabank.shuhuibao.core.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.cdatabank.shuhuibao.core.constants.GlobalConstants;

public class SessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	// 保存当前登录的所有用户
	public static Map<HttpSession, String> loginUser = new HashMap<HttpSession, String>();

	// 用这个作为session中的key

	public static String SESSION_LOGIN_NAME = GlobalConstants.SESSION_LOGIN_CHECK_REPEAT;

	// session创建时调用这个方法
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

	}

	// Session失效或者过期的时候调用的这个方法,
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// 如果session超时, 则从map中移除这个用户
		System.out.println("seesion 失效"+se.getSource());
		System.out.println("seesion 失效"+se.getSession());
		try {
			loginUser.remove(se.getSession());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 执行setAttribute的时候, 当这个属性本来不存在于Session中时, 调用这个方法.
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("attributeAdded value="+se.getValue().toString());
		System.out.println("attributeAdded name="+se.getName().toString());
		// 如果添加的属性是用户名, 则加入map中
		if (se.getName().equals(SESSION_LOGIN_NAME)) {
			loginUser.put(se.getSession(),
					se.getValue().toString());
		}

	}

	// 当执行removeAttribute时调用的方法
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// 如果移除的属性是用户名, 则从map中移除
		System.out.println("seesion removeAttribute"+se.getName());
		if (se.getName().equals(SESSION_LOGIN_NAME)) {
			try {
				loginUser.remove(se.getSession());
			} catch (Exception e) {
			}
		}

	}

	//当执行setAttribute时 ,如果这个属性已经存在, 覆盖属性的时候, 调用这个方法 
	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// 如果改变的属性是用户名, 则跟着改变map 
		  System.out.println("seesion attributeReplaced"+se.getName());
		  if (se.getName().equals(SESSION_LOGIN_NAME)) { 
		   loginUser.put(se.getSession(), se.getValue().toString()); 
		} 

	}

}
