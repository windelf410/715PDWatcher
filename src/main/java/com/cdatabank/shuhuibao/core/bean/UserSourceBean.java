package com.cdatabank.shuhuibao.core.bean;
/**
 * @author gxz
 * UserSource.java
 * 2015-4-1	
 */
public class UserSourceBean {

	private long id;
	
	private String name; 
	
	private String psw;
	

	public UserSourceBean(long id, String name,String psw) {
		super();
		this.id = id;
		this.name=name;
		this.psw = psw;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPsw() {
		return psw;
	}


	public void setPsw(String psw) {
		this.psw = psw;
	}

	
}
