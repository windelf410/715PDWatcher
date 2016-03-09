package com.cdatabank.shuhuibao.core.po;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.sql.Blob;

import javax.ws.rs.FormParam;

import com.cdatabank.shuhuibao.core.vo.PageParam;

@XmlRootElement
public class OrigUser extends PageParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@FormParam("id")
	private Integer id;
	@FormParam("name")
	private String name;
	
	@FormParam("realname")
	private String realname;
	
	@FormParam("pwd")
	private String pwd;
	@FormParam("remark")
	private String remark;
	
	
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
