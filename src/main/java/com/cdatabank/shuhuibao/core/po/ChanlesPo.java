package com.cdatabank.shuhuibao.core.po;

import java.io.Serializable;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdatabank.shuhuibao.core.vo.PageParam;

@XmlRootElement
public class ChanlesPo extends PageParam implements Serializable{

	@FormParam("xh")
	private String xh;
	
	@FormParam("channel")
	private String channel;
	
	@FormParam("batchno")
	private String batchno;
	
	@FormParam("event")
	private String event;
	
	@FormParam("imagid")
	private String imagid;
	
	@FormParam("status")
	private String status;

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}



	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getImagid() {
		return imagid;
	}

	public void setImagid(String imagid) {
		this.imagid = imagid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
