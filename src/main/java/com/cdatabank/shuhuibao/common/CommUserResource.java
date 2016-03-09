package com.cdatabank.shuhuibao.common;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.cdatabank.shuhuibao.core.po.OrigUser;
import com.cdatabank.shuhuibao.core.service.IUserService;

@Path("common/user")
public class CommUserResource {

	@Autowired
	private IUserService service;
	
	@POST 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.TEXT_HTML})	
	@Path("edit")
	public Response addOrEditObject(@BeanParam OrigUser bean){
		int result=0;
		System.out.println("bean==>"+bean);
		String handle="";
		if(bean.getId()==null){
			result=service.addObject(bean);
			handle="添加";
		}else{
			result=service.updateObject(bean);
			handle="修改";
		}
		if(result>0){
			return Response.status(201).entity(handle+"成功").build();
		}else{
			return Response.status(201).entity(handle+"失败").build();
		}
	}
	
	@POST
	@Path("del")
	public Response delete(@FormParam("ids") String ids){
		int result=service.deleteObjects(ids);
		if(result>0){
			return Response.status(200).entity("删除成功").build();
		}else{
			return Response.status(200).entity("删除失败").build();
		}
	}
}
