package com.cdatabank.shuhuibao.core.constants;

public class GlobalConstants {

	public final static  byte ZERO=0;
	
	public final static  byte ONE=1;
	
	public final static  byte TWO=2;
	
	public final static  byte THREE=3;
	
	public final static String SESSION_LOGIN_NAME = "sysUserKey"; 
	
	public final static String SESSION_LOGIN_CHECK_REPEAT = "source_userid"; 
	
	public final static  short USER_EXIST_CODE=2601;
	
	public final static  short USER_NOT_EXIST_CODE=2602;
	
	public final static  short USER_PASSWORD_WRONG_CODE=2603;
	
	public final static  short USER_LOGIN_YET_CODE=2604;
	
	public final static  short USER_USERPWD_NOTNULL_CODE=2605;
	
	public final static  String USER_ADD_SUCCESS="用户添加成功";
	
	public final static  String USER_ADD_FAIL="用户添加失败";
	
	public final static  String USER_EDIT_SUCCESS="用户修改成功";
	
	public final static  String USER_EDIT_FAIL="用户修改失败";
	
	public final static  String SERVICE_EXCEPTION="服务异常，请联系管理员";
	
	public final static  String USER_EXIST_DESC="用户已存在";
	
	public final static  String USER_EXIST_NOT_DESC="用户名不存在";
	
	public final static  String USER_LOGIN_NOT_DESC="登陆已失效，请重新登陆";
	
	public final static  String USER_PASSWORD_WRONG_DESC="密码输入错误";
	
	public final static  String USER_LOGIN_YET_DESC="该账号已登陆";
	
	public final static  String USER_USERPWD_NOTNULL_DESC="用户或密码不能为空";
}
