package com.cdatabank.shuhuibao.core.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SxxinfoVO {
	
	private int id;
	private String xh;
	private String ip;
	private String temp;
	private String logtime;
	private String IMGID;
	private String flag;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	public String getIMGID() {
		return IMGID;
	}
	public void setIMGID(String iMGID) {
		IMGID = iMGID;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
    
	
	
}
