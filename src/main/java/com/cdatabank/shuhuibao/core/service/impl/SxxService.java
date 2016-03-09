package com.cdatabank.shuhuibao.core.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdatabank.shuhuibao.core.dao.ISxxDao;
import com.cdatabank.shuhuibao.core.po.ModuleConfigPo;
import com.cdatabank.shuhuibao.core.po.OrigUser;
import com.cdatabank.shuhuibao.core.service.ISxxService;
import com.cdatabank.shuhuibao.core.vo.PageList;
import com.cdatabank.shuhuibao.core.vo.SxxinfoVO;

@Service
@Transactional
public class SxxService implements ISxxService{
	
	@Resource
	private ISxxDao dao;
	
	@Override
	public int addObject(ModuleConfigPo entity) {
		// TODO Auto-generated method stub
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
	public int updateObject(ModuleConfigPo entity) {
		// TODO Auto-generated method stub
		return dao.updateObject(entity);
	}

	@Override
	public List<ModuleConfigPo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize(ModuleConfigPo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageList<ModuleConfigPo> getPage(ModuleConfigPo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleConfigPo getObjectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SxxinfoVO> getmsmUNConnect(Map map) {
		   
		return dao.getmsmUNConnect(map);
	}

	@Override
	public List<ModuleConfigPo> getmoduleconfigpage() {
		List<ModuleConfigPo> p = dao.getmoduleconfigpage();
		List<ModuleConfigPo> q = new ArrayList<ModuleConfigPo>();
		for (Iterator iterator = p.iterator(); iterator.hasNext();) {
			ModuleConfigPo moduleConfigPo = (ModuleConfigPo) iterator.next();
			if(moduleConfigPo.getFlag().equals("1")){
				moduleConfigPo.setFlag("有效");
			}else{
				moduleConfigPo.setFlag("无效");
			}
			q.add(moduleConfigPo);
		}
		return q;
	}

	@Override
	public List<ModuleConfigPo> readoncunmtroom1(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  dao.readoncunmtroom1(map);
	}

	@Override
	public List<ModuleConfigPo> readoncunmtroom2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  dao.readoncunmtroom2(map);
	}

	@Override
	public List<ModuleConfigPo> readoncunmtroom3(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  dao.readoncunmtroom3(map);
	}

	@Override
	public int leftOn() {
		// TODO Auto-generated method stub
		return dao.leftOn();
	}
	
	@Override
	public int leftoff() {
		// TODO Auto-generated method stub
		return dao.leftoff();
	}

	@Override
	public int righton() {
		// TODO Auto-generated method stub
		return dao.righton();
	}

	@Override
	public int righoff() {
		// TODO Auto-generated method stub
		return dao.rightoff();
	}

	@Override
	public ModuleConfigPo getisOnlin(String dev_id) {
		// TODO Auto-generated method stub
		return dao.getisOnlin(dev_id);
	}


}
