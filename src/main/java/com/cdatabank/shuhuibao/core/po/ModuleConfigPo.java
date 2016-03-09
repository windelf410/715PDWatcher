package com.cdatabank.shuhuibao.core.po;

import java.io.Serializable;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdatabank.shuhuibao.core.vo.PageParam;

@XmlRootElement
public class ModuleConfigPo extends PageParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1548129554377931313L;
	
	@FormParam("id")
	private Integer id;
	@FormParam("ph")
	private String ph;
	@FormParam("xh")
	private String xh;
	@FormParam("dev_id")
	private String dev_id;
	@FormParam("ad_addr")
	private String ad_addr;
	@FormParam("status")
	private String status;
	@FormParam("di_addr")
	private String di_addr;
	@FormParam("temp")
	private String temp;
	@FormParam("logtime")
	private String logtime;
	@FormParam("imgid")
	private String imgid;
	@FormParam("flag")
	private String flag;
	@FormParam("online")
	private String online;
	@FormParam("event")
	private String event;
	@FormParam("batchNo")
	private String batchNo;
	@FormParam("temp_adj")
	private String temp_adj;
	@FormParam("isstart")
	private String isstart;
	
	
	
	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getIsstart() {
		return isstart;
	}

	public void setIsstart(String isstart) {
		this.isstart = isstart;
	}

	public String getTemp_adj() {
		return temp_adj;
	}

	public void setTemp_adj(String temp_adj) {
		this.temp_adj = temp_adj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	

	public String getDev_id() {
		return dev_id;
	}

	public void setDev_id(String dev_id) {
		this.dev_id = dev_id;
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



	public String getImgid() {
		return imgid;
	}

	public void setImgid(String imgid) {
		this.imgid = imgid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getAd_addr() {
		return ad_addr;
	}

	public void setAd_addr(String ad_addr) {
		this.ad_addr = ad_addr;
	}

	public String getDi_addr() {
		return di_addr;
	}

	public void setDi_addr(String di_addr) {
		this.di_addr = di_addr;
	}
	
	
	
}
