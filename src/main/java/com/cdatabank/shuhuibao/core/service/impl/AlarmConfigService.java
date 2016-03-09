package com.cdatabank.shuhuibao.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdatabank.shuhuibao.core.dao.IAlarmConfigDao;
import com.cdatabank.shuhuibao.core.dao.IChanlesDao;
import com.cdatabank.shuhuibao.core.po.AlarmConfigPo;
import com.cdatabank.shuhuibao.core.po.XhLabelPo;
import com.cdatabank.shuhuibao.core.service.IAlarmConfigService;
import com.cdatabank.shuhuibao.core.vo.PageList;
import com.cdatabank.shuhuibao.core.vo.alarmlogVo;

@Service
@Transactional
public class AlarmConfigService implements IAlarmConfigService{
    
	
	@Resource
	IAlarmConfigDao dao;
	
	public AlarmConfigService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addObject(AlarmConfigPo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteObjects(String ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateObject(AlarmConfigPo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AlarmConfigPo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize(AlarmConfigPo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageList<AlarmConfigPo> getPage(AlarmConfigPo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlarmConfigPo getObjectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlarmConfigPo> getAlarmConfig(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return dao.getAlarmConfig(ne);
	}

	@Override
	public List<alarmlogVo> getalarmlog(String ids) {
		List<alarmlogVo> res = dao.getalarmlog(ids);
		List<alarmlogVo> resp = new ArrayList<alarmlogVo>();
		for (Iterator iterator = res.iterator(); iterator.hasNext();) {
			alarmlogVo ala = (alarmlogVo) iterator.next();
			String rem = ala.getRemark();
			String[] xyo = rem.split(",");
			List xy = new ArrayList<String>();
			StringBuffer event = new StringBuffer();
			int len = xyo.length;
			for (int i = 0; i < len; i++) {
				xy.add(xyo[i]);
			}
			if(len<10){
				for (int i =len; i < 10; i++) {
					xy.add("");
				}
			}
			
			
			if(xy.get(0).equals("1")){  //登录
				event.append("事件:登录<br/>").append("用户:").append(ala.getUser());
			}else if(xy.get(0).equals("2")){//下载未完成批次号
				event.append("事件:下载未完成批次号<br/>").append("用户:").append(ala.getUser());
			}else if(xy.get(0).equals("3")){
				event.append("事件:启动一个批次号<br/>").append("用户:").append(ala.getUser());
			}else if(xy.get(0).equals("4")){
				event.append("事件:暂停一个批次号<br/>").append(":").append(xy.get(1)).append(",").append(ala.getUser());
			}else if(xy.get(0).equals("5")){
				event.append("事件:增加批次号测试时间<br/>").append(",批次号").append(xy.get(1)).append("延长时间").append(xy.get(2)).append(",").append(ala.getUser());
			}else if(xy.get(0).equals("6")){
				event.append("事件:批次号测试完成<br/>").append(",批次号").append(xy.get(1)).append(",").append(ala.getUser());
			}else if(xy.get(0).equals("8")){
				event.append("事件:创建新的批次号<br/>").append(",批次号").append(xy.get(1)).append(",<br/>测试电压").append(xy.get(2))
				.append(",<br/>电压误差").append(xy.get(3)).append(",<br/>测试温度").append(xy.get(4))
				.append(",<br/>温度误差").append(xy.get(5)).append(",<br/>测试时间长度").append(xy.get(6))
				.append(",<br/>设备配置字符串").append(xy.get(7)).append(",<br/>").append(ala.getUser());
			}else if(xy.get(0).equals("9")){
				event.append("事件:批次号废品记录").append(",<br/>批次号").append(xy.get(1)).append(",").append(",<br/>废品数量：")
				.append(xy.get(2)).append(",").append(ala.getUser());
			}else if(xy.get(0).equals("7")){
				if(xy.get(1).equals("1")){
					event.append("报警:模块数据没有更新或网络掉线").append(",<br/>批次号:").append(xy.get(2)).append(",<br/>箱号:");
					for (int i = 3; i < xy.size(); i++) {
						if(i+1==xy.size()){
							event.append(xy.get(i));
						}else{
							event.append(xy.get(i)+",");
						}
					}
					event.append(",").append(ala.getUser());
				}else if(xy.get(1).equals("2")){
					event.append("报警:开关量报警").append(",<br/>批次号:").append(xy.get(2)).append(",<br/>箱号:");
					for (int i = 3; i < xy.size(); i++) {
						if(i+1==xy.size()){
							event.append(xy.get(i));
						}else{
							event.append(xy.get(i)+",");
						}
					}
					event.append(",").append(ala.getUser());
				}else if(xy.get(1).equals("3")){
					event.append("报警:温度报警").append(",<br/>批次号:").append(xy.get(2)).append(",<br/>箱号:");
					for (int i = 3; i < xy.size(); i++) {
						if(i+1==xy.size()){
							event.append(xy.get(i));
						}else{
							event.append(xy.get(i)+",");
						}
					}
					event.append(",").append(ala.getUser());
				}else if(xy.get(1).equals("4")){
					event.append("报警:电压报警").append(",<br/>批次号:").append(xy.get(2)).append(",<br/>箱号:");
					for (int i = 3; i < xy.size(); i++) {
						if(i+1==xy.size()){
							event.append(xy.get(i));
						}else{
							event.append(xy.get(i)+",");
						}
					}
					event.append(",").append(ala.getUser());
				}else if(xy.get(1).equals("5")){
					event.append("报警:测试时间完成报警").append(",<br/>批次号:").append(xy.get(2));
					event.append(",<br/>").append(ala.getUser());
				}
			}else if(xy.get(0).equals("10")){
				if(xy.get(1).equals("1")){
					event.append("客户端响应:登录,").append(xy.get(2)).append(",<br/>").append(ala.getUser());;
				}else if(xy.get(1).equals("2")){
					event.append("客户端响应:下载未完成批次号,").append("批次号:");
					for (int i = 2; i < xy.size(); i++) {
						if(i+1==xy.size()){
							event.append(xy.get(i));
						}else{
							event.append(xy.get(i)+",");
						}
					}
					event.append("<br/>").append(ala.getUser());
				}else if(xy.get(1).equals("3")){
					event.append("客户端响应:启动一个批次号,").append(xy.get(2)).append(",<br/>").append(ala.getUser());;
				}else if(xy.get(1).equals("4")){
					event.append("客户端响应:暂停一个批次号报警,").append(xy.get(2)).append(",<br/>").append(ala.getUser());;
				}else if(xy.get(1).equals("5")){
					event.append("客户端响应:增加批次号测试时间,").append(xy.get(2)).append(",<br/>").append(ala.getUser());;
				}else if(xy.get(1).equals("6")){
					event.append("客户端响应:批次号测试完成,").append(xy.get(2)).append(",<br/>").append(ala.getUser());;
				}else if(xy.get(1).equals("7")){
					event.append("客户端响应:报警消息,").append(xy.get(2)).append(",<br/>").append(ala.getUser());;
				}else if(xy.get(1).equals("8")){
					event.append("客户端响应:创建新的批次号,").append(xy.get(2)).append(",<br/>").append(ala.getUser());;
				}else if(xy.get(1).equals("9")){
					event.append("客户端响应:批次号废品记录,").append(xy.get(2)).append(",<br/>").append(ala.getUser());;
				}
				
			}else{
				event.append(rem).append(",<br/>").append(ala.getUser());
			}
			if(!xy.get(0).equals("10")){
				ala.setEvent(event.toString());
				resp.add(ala);
			}
			
		}
		return resp;
	}

	@Override
	public List<AlarmConfigPo> getAlarmConfig(String id) {
		// TODO Auto-generated method stub
		return dao.getAlarmConfigByXh(id);
	}

	@Override
	public List<AlarmConfigPo> getAlarmrecRods(AlarmConfigPo ne) throws ParseException {
		List<AlarmConfigPo> rs = dao.getAlarmrecRods(ne);
		List<AlarmConfigPo> rse = new ArrayList<AlarmConfigPo>();
		SimpleDateFormat df = new SimpleDateFormat("mm:ss");
		for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
			AlarmConfigPo alarmConfigPo = (AlarmConfigPo) iterator.next();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			int time = 0 ;
			if(alarmConfigPo.getFp().equals("0")){
				if(alarmConfigPo.getEndTime()!=null&&alarmConfigPo.getStart()!=null&&!alarmConfigPo.getEndTime().equals("")&&!alarmConfigPo.getStart().equals("")){
					Date b = sdf.parse(alarmConfigPo.getEndTime());
					Date a = sdf.parse(alarmConfigPo.getStart());
					time = (int) (b.getTime() - a.getTime());
				}
				
			}else{
				if(alarmConfigPo.getEndTime()==null||alarmConfigPo.getEndTime().equals("")||alarmConfigPo.getEndTime().equals("0000-00-00 00:00:00 ")){
					if(alarmConfigPo.getFp_time()!=null&&!alarmConfigPo.getFp_time().equals("")){
						Date b = new Date();
						Date a = sdf.parse(alarmConfigPo.getFp_time());
						time = (int) (b.getTime() - a.getTime());
					}
					
				}else{
					if(alarmConfigPo.getStart()!=null&&alarmConfigPo.getFp_time()!=null&&!alarmConfigPo.getEndTime().equals("")&&!alarmConfigPo.getFp_time().equals("")){
						Date b = sdf.parse(alarmConfigPo.getEndTime());
						Date a = sdf.parse(alarmConfigPo.getFp_time());
						time = (int) (b.getTime() - a.getTime());
					}
					
				}
			}
			//int time = Integer.parseInt(aso2.getSj());
			String times="";
			if(time>0){
				double all = (double) (Math.round(time)/3600000.0);
				int  min =  (int) Math.floor(time/3600000);
				int max = (int)Math.floor((all-min)*60);
				String hour =  min>10?min+"":("0"+min);
				String mm =  max>10?max+"":("0"+max);
				 times = hour+":"+ mm;
			}else{
				 
				 times = "00:00";
			}
					alarmConfigPo.setTime(times);
			rse.add(alarmConfigPo);
		}
		return rse;
	}

	@Override
	public List<AlarmConfigPo> getmoduledata(String id) {
		// TODO Auto-generated method stub
		return dao.getmoduledata(id);
	}

	@Override
	public List<AlarmConfigPo> getmoduleconfig(String id) {
		// TODO Auto-generated method stub
	    return dao.getmoduleconfig(id);
	}

	@Override
	public List<AlarmConfigPo> getalarmresing(AlarmConfigPo ne) {
		ne.setFlag("9");
		List<AlarmConfigPo> pflist = dao.getalarmresing(ne);
		ne.setFlag("7");
		List<AlarmConfigPo> qdlist = dao.getalarmresing(ne);
		List<AlarmConfigPo> rslist = new ArrayList<AlarmConfigPo>();
		for (Iterator iterator = pflist.iterator(); iterator.hasNext();) {
			AlarmConfigPo res = new AlarmConfigPo();
			AlarmConfigPo pflistPo = (AlarmConfigPo) iterator.next();
			for (int i = 0; i < qdlist.size(); i++) {
				AlarmConfigPo qdlistPo = (AlarmConfigPo) qdlist.get(i);
				long pft = Long.parseLong(pflistPo.getTime());
				long qdt = Long.parseLong(qdlistPo.getTime());
				if(pft<qdt){
					AlarmConfigPo qderllistPo = (AlarmConfigPo) qdlist.get(i-1);
					long qdert = Long.parseLong(qderllistPo.getTime());
					if(pft>qdert){
						res.setBatchNo(pflistPo.getBatchNo());
						res.setStart(qderllistPo.getStart());
						res.setEndTime(pflistPo.getStart());
						SimpleDateFormat df = new SimpleDateFormat("mm:ss");
						long time = pft-qdert;
						String times = df.format(new Date(time*1000));
						res.setTime(times);
						break;
					}
				}
			}
			rslist.add(res);
			
		}
		// TODO Auto-generated method stub
		return rslist;
	}

	@Override
	public List<XhLabelPo> getCanselectXH() {
		// TODO Auto-generated method stub
		return dao.getCanselectXH();
	}

	@Override
	public int getAlarmrecRodsTotal(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return dao.getAlarmrecRodsTotal(ne);
	}

	@Override
	public List<AlarmConfigPo> gettempList(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return dao.gettempList(ne);
	}

	@Override
	public List<AlarmConfigPo> getVleftList(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return dao.getVleftList(ne);
	}

	@Override
	public List<AlarmConfigPo> getTempNow(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return dao.getTempNow(ne);
	}

	@Override
	public List<AlarmConfigPo> getVleftNewinfo(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return dao.getVleftNewinfo(ne);
	}

	@Override
	public List<AlarmConfigPo> getBhAllinfo(AlarmConfigPo ne) {
		return dao.getBhAllinfo(ne);
	}

	@Override
	public List<AlarmConfigPo> getUserChanle(int xh_id) {
		// TODO Auto-generated method stub
		return dao.getUserChanle(xh_id);
	}

	@Override
	public List<AlarmConfigPo> getScreeninfo(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return dao.getScreeninfo(ne);
	}

	@Override
	public int getScreeninfoTotal(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return  dao.getScreeninfoTotal(ne);
	}

	@Override
	public List<AlarmConfigPo> getFPCS(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return dao.getFPCS(ne);
	}

	@Override
	public List<AlarmConfigPo> getWarrinAllinfo(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return dao.getWarrinAllinfo(ne);
	}

	@Override
	public int getAlarmConfigTotal(AlarmConfigPo ne) {
		// TODO Auto-generated method stub
		return dao.getAlarmConfigTotal(ne);
	}

	@Override
	public List<AlarmConfigPo> getBatchFpSByXH(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.getBatchFpSByXH(map);
	}

	@Override
	public AlarmConfigPo getTestInfoById(String id) {
		// TODO Auto-generated method stub
		return dao.getTestInfoById(id);
	}

	@Override
	public List<AlarmConfigPo> getWarrinAllinfoByBid(int id) {
		// TODO Auto-generated method stub
		return  dao.getWarrinAllinfoByBid(id);
	}

	@Override
	public List<AlarmConfigPo> getfpbcByBid(int id) {
		// TODO Auto-generated method stub
		return  dao.getfpbcByBid(id);
	}

}
