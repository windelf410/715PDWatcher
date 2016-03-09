package com.cdatabank.shuhuibao.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdatabank.shuhuibao.core.dao.IChanlesDao;
import com.cdatabank.shuhuibao.core.po.ChanlesPo;
import com.cdatabank.shuhuibao.core.service.IChanleservice;
import com.cdatabank.shuhuibao.core.vo.PageList;
import com.cdatabank.shuhuibao.core.vo.SxxinfoVO;

@Service
@Transactional
public class Chanleservice implements IChanleservice{
	
	@Resource
	IChanlesDao dao;
	
	public Chanleservice() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addObject(ChanlesPo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteObjects(String ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateObject(ChanlesPo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChanlesPo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize(ChanlesPo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageList<ChanlesPo> getPage(ChanlesPo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChanlesPo getObjectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChanlesPo> getChannelInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.getChannelInfo(map);
	}

	@Override
	public List<ChanlesPo> getisOnline(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.getisOnline(map);
	}

	@Override
	public List<ChanlesPo> getisreson3mm(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.getisreson3mm(map);
	}

}
