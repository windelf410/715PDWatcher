package com.cdatabank.shuhuibao.core.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.cdatabank.shuhuibao.core.po.AlarmConfigPo;
import com.cdatabank.shuhuibao.core.po.XhLabelPo;
import com.cdatabank.shuhuibao.core.service.base.IBaseService;
import com.cdatabank.shuhuibao.core.vo.alarmlogVo;

public interface IAlarmConfigService  extends IBaseService<AlarmConfigPo>{

	List<AlarmConfigPo> getAlarmConfig(AlarmConfigPo ne);

	List<alarmlogVo> getalarmlog(String ids);

	List<AlarmConfigPo> getAlarmConfig(String id);

	List<AlarmConfigPo> getAlarmrecRods(AlarmConfigPo ne) throws ParseException;

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
