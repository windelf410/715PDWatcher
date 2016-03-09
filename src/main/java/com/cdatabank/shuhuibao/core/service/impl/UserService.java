package com.cdatabank.shuhuibao.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cdatabank.shuhuibao.core.dao.IUserDao;
import com.cdatabank.shuhuibao.core.po.OrigUser;
import com.cdatabank.shuhuibao.core.service.IUserService;
import com.cdatabank.shuhuibao.core.util.CommonUtil;
import com.cdatabank.shuhuibao.core.vo.PageList;

@Service
@Transactional
public class UserService implements IUserService {

	@Resource
	private IUserDao dao;
	@Override
	public int addObject(OrigUser entity) {
		
		return dao.addObject(entity);
	}

	@Override
	public int deleteObjects(String ids) {
		String [] arr=null;
//		if(StringUtils.isNotBlank(ids)&&ids.contains(",")){
//			arr=ids.split(",");
//			
//		}else{
//			arr=new String[]{ids};
//		}
		arr=new String[]{ids};
		return dao.deleteObjects(arr);
	}

	@Override
	public int updateObject(OrigUser entity) {
		return dao.updateObject(entity);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<OrigUser> getAll() {
		return dao.getAll(null);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int getSize(OrigUser entity) {
		return dao.getSize(entity);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public PageList<OrigUser> getPage(OrigUser entity) {
		int page=entity.getPage();
		int rows=entity.getRows();
		//System.out.println("page=====>"+page);
		//System.out.println("rows=====>"+rows);
		int begin = (page-1)*rows;
		int storp = rows*page;
		page=(page-1)*rows;
		entity.setPage(begin);
		entity.setRows(storp);
		List<OrigUser> list=dao.getAll(entity);
		PageList<OrigUser> pageList=new PageList<OrigUser>();
		pageList.setRows(list);
		pageList.setTotal(dao.getSizeall());
		return pageList;
		//return CommonUtil.getPageList();
	}

	@Override
	public OrigUser getUserByName(String name) {
		
		return dao.getObjectByName(name);
	}

	@Override
	public OrigUser getObjectById(String id) {
		
		return dao.getObjectById(Integer.parseInt(id));
	}

}
