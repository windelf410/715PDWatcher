package com.cdatabank.shuhuibao.manage;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.cdatabank.shuhuibao.core.constants.GlobalConstants;
import com.cdatabank.shuhuibao.core.po.AlarmConfigPo;
import com.cdatabank.shuhuibao.core.po.ModuleConfigPo;
import com.cdatabank.shuhuibao.core.po.OrigUser;
import com.cdatabank.shuhuibao.core.po.XhLabelPo;
import com.cdatabank.shuhuibao.core.service.IAlarmConfigService;
import com.cdatabank.shuhuibao.core.service.ISxxService;
import com.cdatabank.shuhuibao.core.service.IUserService;
import com.cdatabank.shuhuibao.core.vo.PageList;
import com.cdatabank.shuhuibao.core.vo.Status;
import com.cdatabank.shuhuibao.core.vo.SxxinfoVO;
import com.cdatabank.shuhuibao.core.vo.alarmlogVo;

@Path("manage/user")
public class UserResource {

	@Autowired
	private IUserService service;
	
	@Autowired
	private ISxxService issservice;
	
	@Autowired
	private IAlarmConfigService almservice;
	
	
	@POST
	@Path("page")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})
	public PageList<OrigUser> getPageList(@BeanParam OrigUser bean){
		//System.out.println("page=="+page);
		//System.out.println("rows=="+rows);
		//bean.setPage(Integer.parseInt(page));
		//bean.setRows(Integer.parseInt(rows));
		//System.out.println("========>bean"+bean);
		return service.getPage(bean);
	}
	
	
	
	@POST
	@Path("getall")
	@Produces({MediaType.APPLICATION_JSON})
	public List<OrigUser> getAll(){
		return service.getAll();
	}
	
	@POST
	@Path("getmoduleconfigpage")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ModuleConfigPo> getmoduleconfigpage(){
		return issservice.getmoduleconfigpage();
	}
	
	@POST 
	@Path("updatemoduleconfigpage")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})	
	public Response updatemoduleconfigpage(@FormParam("id") String id,
		    @FormParam("xh") String xh,
		    @FormParam("dev_id") String dev_id,
		    @FormParam("ad_addr") String ad_addr,
		    @FormParam("di_addr") String di_addr,
		    @FormParam("temp_adj") String temp_adj,
		    @FormParam("flag") String flag){
		try{
			ModuleConfigPo bean =new ModuleConfigPo();
			if(id==null||id.equals("")){
				bean.setXh(xh);
				bean.setDev_id(dev_id);
				bean.setAd_addr(ad_addr);
				bean.setDi_addr(di_addr);
				bean.setTemp_adj(temp_adj);
				bean.setFlag(flag);
			}else{
				bean.setId(Integer.parseInt(id));
				bean.setXh(xh);
				bean.setDev_id(dev_id);
				bean.setAd_addr(ad_addr);
				bean.setDi_addr(di_addr);
				bean.setTemp_adj(temp_adj);
				bean.setFlag(flag);
			}
			
			
			int result=0;
			System.out.println("修改");
			System.out.println("bean==>"+bean); 
			if(null==id||id.equals("")){
				result=issservice.addObject(bean);
			}else{
				result=issservice.updateObject(bean);
			}
			
			//System.out.println("bean.getBirthday()==null="+(bean.getBirthday()==null));
			//System.out.println("bean.getBirthday().equals('null')="+("null".equals(bean.getBirthday())));
			//System.out.println("bean.getBirthday().equals('')="+("".equals(bean.getBirthday())));
			//System.out.println("userid===>"+bean.getUser_ID());
			//String str="null";
			if(result>0){
//				 Runtime rt = Runtime.getRuntime(); 
//				    try {        
//				      String file = "D:/apache-tomcat-7.0.55/webapps/hyd/reload.bat"; 
//				      rt.exec("cmd.exe /c start " + file); 
//				    } catch (IOException e) { 
//				      e.printStackTrace(); 
//				    } 
				return Response.status(201).entity("成功").build();
			}else{
				return Response.status(201).entity("失败").build();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			
		}
		
	}
	@POST
	@Path("delmoduleconfigpage")
	public Response delmoduleconfigpage(@FormParam("ids") String ids){
		int result=issservice.deleteObjects(ids);
		if(result>0){
			return Response.status(200).entity("删除成功").build();
		}else{
			return Response.status(200).entity("删除失败").build();
		}
	}
	@POST
	@Path("getalarmconfig")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAlarmConfig(@FormParam("start") String start,
											  @FormParam("end") String end,
											  @FormParam("batchNo") String batchNo,
											  @FormParam("page") String page,
											  @FormParam("rows") String rows){
		
		int begin = (Integer.parseInt(page)-1)*Integer.parseInt(rows);
		int storp = Integer.parseInt(rows)*Integer.parseInt(page);
		AlarmConfigPo ne = new AlarmConfigPo();
		ne.setStart(start);
		ne.setEndTime(end);
		ne.setBatchNo(batchNo);
		ne.setPage(begin);
		ne.setRows(storp);
		PageList<AlarmConfigPo> list = new PageList<AlarmConfigPo>();
		list.setTotal(almservice.getAlarmConfigTotal(ne));
		list.setRows(almservice.getAlarmConfig(ne));
		return Response.status(200).entity(list).build();
	}
	/**
	 * 筛选历史查询
	 * @param start
	 * @param end
	 * @param batchNo
	 * @param page
	 * @param rows
	 * @return
	 */
	@POST
	@Path("getscreeninfo")
	@Produces({MediaType.APPLICATION_JSON})
	public Response  getScreeninfo(@FormParam("start") String start,
											  @FormParam("end") String end,
											  @FormParam("batchNo") String batchNo,
											  @FormParam("xh") String xh,
											  @FormParam("page") String page,
											  @FormParam("rows") String rows){
		
		int begin = (Integer.parseInt(page)-1)*Integer.parseInt(rows);
		int storp = Integer.parseInt(rows)*Integer.parseInt(page);
		AlarmConfigPo ne = new AlarmConfigPo();
		ne.setStart(start);
		ne.setEndTime(end);
		ne.setBatchNo(batchNo);
		ne.setXh(xh);
		ne.setPage(begin);
		ne.setRows(storp);
		PageList<AlarmConfigPo> list = new PageList<AlarmConfigPo>();
		list.setTotal(almservice.getScreeninfoTotal(ne));
		list.setRows(almservice.getScreeninfo(ne));
		return Response.status(200).entity(list).build();
	}
	/**
	 * 获取可以被选择的箱号
	 * @param start
	 * @param end
	 * @param batchNo
	 * @return
	 */
	@POST
	@Path("getcanselectXH")
	@Produces({MediaType.APPLICATION_JSON})
	public List<XhLabelPo> getCanselectXH(){
		List<XhLabelPo>  resq = new ArrayList<XhLabelPo>();
		List<XhLabelPo>  res =  almservice.getCanselectXH();
		for (Iterator iterator = res.iterator(); iterator.hasNext();) {
			XhLabelPo xhLabelPo = (XhLabelPo) iterator.next();
			List<AlarmConfigPo> pl =  almservice.getUserChanle(xhLabelPo.getXh_id());
			String ap = ""+xhLabelPo.getXh_id();
			if(pl.size()>0){
			for (Iterator poit = pl.iterator(); poit.hasNext();) {
				AlarmConfigPo alarmConfigPo = (AlarmConfigPo) poit.next();
				String[] chanle=alarmConfigPo.getDevices().split("-");
				for(int i=0;i<30;i++){
					if(i<10||i>19){
						int isin=0;
						for(int j=1;j<chanle.length;j++){
							if(chanle[j].equals(i+"")){
								isin=1;
								break;
							}
						}
						if(isin==0){
							ap=ap+"-"+i;
						}
					}
				}
			}
			}else{
				resq.add(xhLabelPo);
			}
			if(ap.split("-").length>1){
				resq.add(xhLabelPo);
			}
			
			
		}
		
		return res;
	}
	/**
	 * 获取可以被选择的箱号
	 * @param start
	 * @param end
	 * @param batchNo
	 * @return
	 */
	@POST
	@Path("getcanselectXHAll")
	@Produces({MediaType.APPLICATION_JSON})
	public List<XhLabelPo> getcanselectXHAll(){
		List<XhLabelPo>  res =  almservice.getCanselectXH();
	
		
		return res;
	}
	/**
	 * 获取箱号当期运行中的已选通道
	 * @param start
	 * @param end
	 * @param batchNo
	 * @return
	 */
	@POST
	@Path("getxhDevice")
	@Produces({MediaType.APPLICATION_JSON})
	public List<AlarmConfigPo> getxhDevice(@FormParam("XH") String xh){
		List<AlarmConfigPo> pl =  almservice.getUserChanle(Integer.parseInt(xh));
		return pl;
	}
	/**
	 * 历史数据统计
	 * @param start
	 * @param end
	 * @param batchNo
	 * @return
	 * @throws ParseException 
	 */
	@POST
	@Path("getalarmrecrods")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAlarmrecRods(@FormParam("start") String start,
												@FormParam("end") String end,
												@FormParam("batchNo") String batchNo,
												@FormParam("page") String page,
												@FormParam("xh") String xh,
												@FormParam("rows") String rows) throws ParseException{
		int begin = (Integer.parseInt(page)-1)*Integer.parseInt(rows);
		int storp = Integer.parseInt(rows);
		AlarmConfigPo ne = new AlarmConfigPo();
		if(start==null&&end==null&&batchNo==null&&xh==null){
			PageList<AlarmConfigPo> list = new PageList<AlarmConfigPo>();
			list.setTotal(0);
			return Response.status(200).entity(list).build();
		}
		ne.setStart(start);
		ne.setEndTime(end);
		ne.setBatchNo(batchNo);
		ne.setXh(xh);
		ne.setPage(begin);
		ne.setRows(storp);
		PageList<AlarmConfigPo> list = new PageList<AlarmConfigPo>();
		list.setTotal(almservice.getAlarmrecRodsTotal(ne));
		list.setRows(almservice.getAlarmrecRods(ne));
		return Response.status(200).entity(list).build();
	}
	/**
	 * 单条实验数据表
	 * @param start
	 * @param end
	 * @param batchNo
	 * @return
	 */
	@POST
	@Path("getalarmresing")
	@Produces({MediaType.APPLICATION_JSON})
	public List<AlarmConfigPo> getalarmresing(@FormParam("bid") String bid ){
		AlarmConfigPo ne = new AlarmConfigPo();
		ne.setId(Integer.parseInt(bid));
		
		return almservice.getalarmresing(ne);
	}
	/**
	 * 获取1小时内指定箱号下温度变化情况
	 * @param xh
	 * @return
	 */
	@POST
	@Path("gettempList")
	@Produces({MediaType.APPLICATION_JSON})
	public Response gettempList(@FormParam("XH") String xh ){
		AlarmConfigPo ne = new AlarmConfigPo();
		ne.setId(Integer.parseInt(xh));
		List<AlarmConfigPo> gets = almservice.gettempList(ne);
		List rest  = new ArrayList<>();
		for (Iterator iterator = gets.iterator(); iterator.hasNext();) {
			AlarmConfigPo ap = (AlarmConfigPo) iterator.next();
			
			String[] s = {ap.getSj(),ap.getWd()};
			rest.add(s);
		}
		
		
		return Response.status(200).entity(rest).build();
	}
	/**
	 * 获取最新温度数据，实时绘制到图标上
	 * @param xh
	 * @return
	 */
	@POST
	@Path("gettempnow")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTempNow(@FormParam("XH") String xh ){
		AlarmConfigPo ne = new AlarmConfigPo();
		ne.setId(Integer.parseInt(xh));
		List<AlarmConfigPo> gets = almservice.getTempNow(ne);
		List rest  = new ArrayList<>();
		for (Iterator iterator = gets.iterator(); iterator.hasNext();) {
			AlarmConfigPo ap = (AlarmConfigPo) iterator.next();
			
			String[] s = {ap.getSj(),ap.getWd()};
			rest.add(s);
		}
		
		System.out.println("---------------temp");
		return Response.status(200).entity(rest).build();
	}
	/**
	 * 获取1小时内指定箱号下左1到左10电压变化情况
	 * @param xh
	 * @return
	 */
	@POST
	@Path("getvleftList")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getVleftList(@FormParam("XH") String xh ){
		AlarmConfigPo ne = new AlarmConfigPo();
		ne.setId(Integer.parseInt(xh));
		List<AlarmConfigPo> gets = almservice.getVleftList(ne);
		return Response.status(200).entity(gets).build();
	}
	/**
	 * 获取指定批次号下箱体实验时间内所有数据
	 * @param xh
	 * @return
	 */
	@POST
	@Path("getbhallinfo")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getBhAllinfo(@FormParam("XH") String xh,
								 @FormParam("batchNo") String batchNo,
								 @FormParam("start") String start,
								 @FormParam("ID") String id,
								 @FormParam("end") String end ){
		AlarmConfigPo ne = new AlarmConfigPo();
		ne.setId(Integer.parseInt(xh));
		ne.setStart(start);
		if(!end.equals("0000-00-00 00:00:00 ")){
			ne.setEndTime(end);
		}else{
			ne.setEndTime(null);
		}
		
		ne.setBatchNo(batchNo);
		List<AlarmConfigPo> gets = almservice.getBhAllinfo(ne);
		List<AlarmConfigPo> warrings = almservice.getWarrinAllinfoByBid(Integer.parseInt(id));
		List<AlarmConfigPo> resw =  new ArrayList<AlarmConfigPo>();
		List<AlarmConfigPo> res =  new ArrayList<AlarmConfigPo>();
		String uptime ="";
		String upeven = "";
		for (int i=0;i< warrings.size() ;i++) {
			AlarmConfigPo ap = (AlarmConfigPo)warrings.get(i);
			if(i==0){
				uptime=ap.getSj();
				upeven = ap.getEvent();
				if(upeven.equals("1")){
					resw.add(ap);
				}
			}else{
				String time =ap.getSj();
				String even = ap.getEvent();
				if(even.equals("1")){
					if(upeven.equals("0")){
						if(!time.equals(uptime)){
							resw.add(ap);
							uptime=ap.getSj();
							upeven = ap.getEvent();
						}else{
							uptime=ap.getSj();
							upeven = ap.getEvent();
						}
					}else{
						uptime=ap.getSj();
						upeven = ap.getEvent();
					}
					
				}else{
					if(!uptime.equals(time)){
						uptime=ap.getSj();
						upeven = ap.getEvent();
					}
				}
			}
			
			
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<resw.size();i++){
			AlarmConfigPo ap = (AlarmConfigPo)resw.get(i);
			Date date1 = null;
			Date date2 = null;
			for(int j=0;j<gets.size();j++){
				AlarmConfigPo op = (AlarmConfigPo)gets.get(j);
				
				 try {
				     date1 = (Date) format.parse(ap.getSj());
				     date2 = (Date) format.parse(op.getSj());
				     long temp = date2.getTime() - date1.getTime(); 
				     temp=temp/1000;
				     if(temp>0){
				    	 gets.get(j).setEvent("1");
				    	 break;
				     }
				    } catch (ParseException e) {
				     e.printStackTrace();
				    }



			}
		}
		
	
		return Response.status(200).entity(gets).build();
	}
	/**
	 * 获取指定实验报废信息
	 * @param xh
	 * @return
	 */
	@POST
	@Path("gettestandfp")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTestAndFPById(@FormParam("ID") String ID
								){
		
		AlarmConfigPo testinfo = almservice.getTestInfoById(ID);
		List<AlarmConfigPo> warrings = almservice.getWarrinAllinfoByBid(testinfo.getId());
		List<AlarmConfigPo> fpbc = almservice.getfpbcByBid(testinfo.getId());
		List<AlarmConfigPo> resw =  new ArrayList<AlarmConfigPo>();
		String uptime ="";
		String upeven = "";

		for (int i=0;i< warrings.size() ;i++) {
			AlarmConfigPo ap = (AlarmConfigPo)warrings.get(i);
			if(i==0){
				uptime=ap.getSj();
				upeven = ap.getEvent();
				if(upeven.equals("1")){
					resw.add(ap);
				}
			}else{
				String time =ap.getSj();
				String even = ap.getEvent();
				if(even.equals("1")){
					if(upeven.equals("0")){
						if(!time.equals(uptime)){
							resw.add(ap);
							uptime=ap.getSj();
							upeven = ap.getEvent();
						}else{
							uptime=ap.getSj();
							upeven = ap.getEvent();
						}
					}else{
						uptime=ap.getSj();
						upeven = ap.getEvent();
					}
					
				}else{
					if(!uptime.equals(time)){
						uptime=ap.getSj();
						upeven = ap.getEvent();
					}
				}
			}
			
			
		}
		for (int i = 0; i < fpbc.size(); i++) {
			AlarmConfigPo ap = (AlarmConfigPo)fpbc.get(i);
			String[] asize = ap.getEvent().split(",");
			int len = asize.length;
			ap.setEvent(asize[len-1]);
			resw.add(ap);
		}

		Map<String,Object> ls =new HashMap<String,Object>();
		ls.put("AlarmConfigPo", testinfo);
		ls.put("fpinfo", resw);
		return Response.status(200).entity(ls).build();//new Status(MyConstant.ONE,user)
		
		
	
	}
	/**
	 * 获取最新指定箱号下左1到左20电压变化情况
	 * @param xh
	 * @return
	 */
	@POST
	@Path("getvleftnewinfo")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getVleftNewinfo(@FormParam("XH") String xh ){
		AlarmConfigPo ne = new AlarmConfigPo();
		ne.setId(Integer.parseInt(xh));
		List<AlarmConfigPo> gets = almservice.getVleftNewinfo(ne);
		return Response.status(200).entity(gets).build();
	}
	//离线线设备
	@POST 
	@Path("getalarmlog")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})	
	public Response getalarmlog( @FormParam("ids") String ids){
		try{

			List<alarmlogVo> res = almservice.getalarmlog(ids);//查找离弦设备
			return Response.status(200).entity(res).build();//new Status(MyConstant.ONE,user)
		}catch(Exception e){
			e.printStackTrace();  
			return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
		}
		
	}
}
