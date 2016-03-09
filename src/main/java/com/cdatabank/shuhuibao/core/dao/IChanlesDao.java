package com.cdatabank.shuhuibao.core.dao;

import java.util.List;
import java.util.Map;

import com.cdatabank.shuhuibao.core.dao.base.IBaseDao;
import com.cdatabank.shuhuibao.core.po.ChanlesPo;

public interface IChanlesDao extends IBaseDao<ChanlesPo> {

	List<ChanlesPo> getChannelInfo(Map<String, Object> map);

	List<ChanlesPo> getisOnline(Map<String, Object> map);

	List<ChanlesPo> getisreson3mm(Map<String, Object> map);

	

}
