package com.cdatabank.shuhuibao.core.po;

import java.io.Serializable;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdatabank.shuhuibao.core.vo.PageParam;

@XmlRootElement
public class XhLabelPo extends PageParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669413210197278852L;

	@FormParam("xh_id")
	private int xh_id;
	
	@FormParam("xh_name")
	private String xh_name;
	
	@FormParam("divice")
	private String divice;

	
	
	public String getDivice() {
		return divice;
	}

	public void setDivice(String divice) {
		this.divice = divice;
	}

	public int getXh_id() {
		return xh_id;
	}
	
	public void setXh_id(int xh_id) {
		this.xh_id = xh_id;
	}

	public String getXh_name() {
		return xh_name;
	}

	public void setXh_name(String xh_name) {
		this.xh_name = xh_name;
	}

	
	
}
