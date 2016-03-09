package com.cdatabank.shuhuibao.core.dao;

import java.util.List;
import java.util.Map;

import com.cdatabank.shuhuibao.core.dao.base.IBaseDao;
import com.cdatabank.shuhuibao.core.po.AlarmConfigPo;
import com.cdatabank.shuhuibao.core.po.XhLabelPo;
import com.cdatabank.shuhuibao.core.vo.alarmlogVo;

public interface IAlarmConfigDao extends IBaseDao<AlarmConfigPo> {

	List<AlarmConfigPo> getAlarmConfig(AlarmConfigPo ne);
	
	List<AlarmConfigPo> getAlarmConfigByXh(String xh);

	List<alarmlogVo> getalarmlog(String batchNo);

	List<AlarmConfigPo> getAlarmrecRods(AlarmConfigPo ne);

	List<AlarmConfigPo> getmoduledata(String id);

	List<AlarmConfigPo> getmoduleconfig(String id);

	List<AlarmConfigPo> getalarmresing(AlarmConfigPo ne);

	List<XhLabelPo> getCanselectXH();

	int getAlarmrecRodsTotal(AlarmConfigPo ne);

	List<AlarmConfigPo> gettempList(AlarmConfigPo ne);

	List<AlarmConfigPo> getVleftList(AlarmConfigPo ne);

	List<AlarmConfigPo> getTempNow(AlarmConfigPo ne);

	List<AlarmConfigPo> getVleftNewinfo(AlarmConfigPo ne);

	List<AlarmConfigPo> getBhAllinfo(AlarmConfigPo ne);

	List<AlarmConfigPo> getUserChanle(int xh_id);

	List<AlarmConfigPo> getScreeninfo(AlarmConfigPo ne);

	int getScreeninfoTotal(AlarmConfigPo ne);

	List<AlarmConfigPo> getFPCS(AlarmConfigPo ne);

	List<AlarmConfigPo> getWarrinAllinfo(AlarmConfigPo ne);

	int getAlarmConfigTotal(AlarmConfigPo ne);

	List<AlarmConfigPo> getBatchFpSByXH(Map<String, Object> map);

	AlarmConfigPo getTestInfoById(String id);

	List<AlarmConfigPo> getWarrinAllinfoByBid(int id);

	List<AlarmConfigPo> getfpbcByBid(int id);


}
