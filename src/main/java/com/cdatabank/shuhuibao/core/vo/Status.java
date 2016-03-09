package com.cdatabank.shuhuibao.core.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status {

	private int code;//状态码
	
	private String desc;//状态描述

	
	public Status() {
		
	}

	public Status(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
