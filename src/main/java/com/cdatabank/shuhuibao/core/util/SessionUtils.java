package com.cdatabank.shuhuibao.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.cdatabank.shuhuibao.core.constants.GlobalConstants;

public class SessionUtils {

	/**
	 * 处理验证码
	 * @param request
	 * @return
	 */
	public static boolean isCheckNum(HttpServletRequest request) {
		//获取已经存在的session
		HttpSession session=request.getSession(false);
		
		if(session==null){
			return false;
		}
		
		String check_number_key=(String)session.getAttribute("CHECK_NUMBER_KEY");
		if(StringUtils.isBlank(check_number_key)){
			return false;
		}
		
		//获取jsp页面文本框中输入的值
		//<input name="checkNum" type="text" value="" id="checkNum" style="width: 80">
		String saved=request.getParameter("checkNum");
		if(StringUtils.isBlank(saved)){
			return false;
		}
		
		//比对session中存放的值和页面文本框输入的验证码的值
		return check_number_key.equalsIgnoreCase(saved);
		
	}

	/**
	 * 保存当前登录用户的信息到session中
	 * @param request
	 * @param sysUser
	 */
	public static void setSysUserToSession(HttpServletRequest request,Object sysUser) {
        HttpSession session=request.getSession();
		if(sysUser==null){
			return;
		} 
		session.setAttribute(GlobalConstants.SESSION_LOGIN_NAME, sysUser);
	}

	/**
	 * 从session中获取当前登陆用户的信息
	 * @param request
	 * @return
	 */
	public static Object getSysUserFormSession(HttpServletRequest request) {
		  HttpSession session=request.getSession(false);
		  if(session==null){
			  return null;
		  }
		  Object SysUser=session.getAttribute(GlobalConstants.SESSION_LOGIN_NAME);
		  return SysUser;
	}

	/**
	 * 用于判断是否重复登陆，因为门户和后台用的不是一个表，他们的主键id有可能相同，所以要指定来源加id来唯一标识
	 * @param request
	 * @param userId
	 */
	public static void checkUserRepeat(HttpServletRequest request,String userId) {
        HttpSession session=request.getSession();
		if(userId==null){
			return;
		} 
		String source=request.getParameter("source");
		if(StringUtils.isNotBlank(source)){
			userId=source+"_"+userId;
		}else{
			
		}
		session.setAttribute(GlobalConstants.SESSION_LOGIN_CHECK_REPEAT, userId);
	}
}
