package com.cdatabank.shuhuibao.core.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class alarmlogVo {

	private String acttime;
	private String event ;
	private String status ;
	private String remark ;
	private String user ;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getActtime() {
		return acttime;
	}
	public void setActtime(String acttime) {
		this.acttime = acttime;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
