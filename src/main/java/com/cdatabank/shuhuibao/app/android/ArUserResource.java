package com.cdatabank.shuhuibao.app.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdatabank.shuhuibao.core.constants.GlobalConstants;  
import com.cdatabank.shuhuibao.core.po.AlarmConfigPo;
import com.cdatabank.shuhuibao.core.po.ChanlesPo;
import com.cdatabank.shuhuibao.core.po.ModuleConfigPo;
import com.cdatabank.shuhuibao.core.po.OrigUser;
import com.cdatabank.shuhuibao.core.service.IAlarmConfigService;
import com.cdatabank.shuhuibao.core.service.IChanleservice;
import com.cdatabank.shuhuibao.core.service.ISxxService;
import com.cdatabank.shuhuibao.core.service.IUserService;
import com.cdatabank.shuhuibao.core.util.CommonUtil;
import com.cdatabank.shuhuibao.core.util.SessionUtils;
import com.cdatabank.shuhuibao.core.vo.Status;
import com.cdatabank.shuhuibao.core.vo.SxxinfoVO;

@Path("app/user")
public class ArUserResource {

	@Autowired
	private IUserService service;
	
	@Autowired
	private ISxxService sxxservice;
	
	@Autowired
	private IChanleservice chanleservice;
	
	@Autowired
	private IAlarmConfigService alarmConfigService;
	
	@Context
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(ArUserResource.class);

	//注册
	@POST 
	@Path("register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})	
	public Response addUser(@BeanParam OrigUser bean){
		try{
			
			System.out.println("添加");
			
			System.out.println("bean==>"+bean);
			System.out.println("StringUtils.isBlank(bean.getLoginID())==>"+StringUtils.isBlank(bean.getName()));
			int result=0;
			if(StringUtils.isBlank(bean.getName())||StringUtils.isBlank(bean.getPwd()))
			{
				return Response.status(200).entity(new Status(GlobalConstants.USER_USERPWD_NOTNULL_CODE,GlobalConstants.USER_USERPWD_NOTNULL_DESC)).build();
			}else{
				OrigUser user=service.getUserByName(bean.getName());
				System.out.println("user==>"+user);
				if(user==null){
					
					result=service.addObject(bean);
					//System.out.println("userid===>"+bean.getUser_ID());
					if(result>0){
						return Response.status(200).entity(new Status(GlobalConstants.ONE,bean.getName()+"")).build();
					}else{
						return Response.status(200).entity(new Status(GlobalConstants.ZERO,GlobalConstants.USER_ADD_FAIL)).build();
					}
				}else{
					return Response.status(200).entity(new Status(GlobalConstants.USER_EXIST_CODE,GlobalConstants.USER_EXIST_DESC)).build();
				}
			}
			
			
		}catch(Exception e){
			logger.error(e.toString());
			e.printStackTrace();
			return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			
		}
		
	}
	
	
	@POST 
	@Path("edit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})	
	public Response editUser(@FormParam("id") String id,
		    @FormParam("name") String name,
		    @FormParam("realname") String realname,
		    @FormParam("pwd") String psw,
		    @FormParam("remark") String remark){
		try{
			OrigUser bean =new OrigUser();
			if(id==null||id.equals("")){
				bean.setName(name);
				bean.setPwd(psw);
				bean.setRemark(remark);
				bean.setRealname(realname);
			}else{
				bean.setId(Integer.parseInt(id));
				bean.setName(name);
				bean.setPwd(psw);
				bean.setRemark(remark);
				bean.setRealname(realname);
			}
			
			
			int result=0;
			System.out.println("修改");
			System.out.println("bean==>"+bean); 
			if(bean.getId()==null){
				result=service.addObject(bean);
			}else{
				result=service.updateObject(bean);
			}
			
			//System.out.println("bean.getBirthday()==null="+(bean.getBirthday()==null));
			//System.out.println("bean.getBirthday().equals('null')="+("null".equals(bean.getBirthday())));
			//System.out.println("bean.getBirthday().equals('')="+("".equals(bean.getBirthday())));
			//System.out.println("userid===>"+bean.getUser_ID());
			//String str="null";
			if(result>0){
				return Response.status(201).entity("成功").build();
			}else{
				return Response.status(201).entity("失败").build();
			}
			
		}catch(Exception e){
			logger.error(e.toString());
			e.printStackTrace();
			return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			
		}
		
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})	
	public Response getUserById(@PathParam("id")String id){
		try{
			return Response.status(200).entity(service.getObjectById(id)).build();
		}catch(Exception e){
			logger.error(e.toString());
			e.printStackTrace();
			return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
		}
		
	}
	
	//登陆
	@POST 
	@Path("login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})	
	public Response login(@FormParam("loginID") String loginID,@FormParam("password") String password,@Context HttpServletResponse response ){
		try{
			OrigUser user=service.getUserByName(loginID);
			System.out.println("登陆user===》"+user);
			if(user==null){
				response.sendRedirect("../../../index.jsp?user=1");
				//response.sendRedirect("../../../manage/index.html");
				return Response.status(200).entity(new Status(GlobalConstants.USER_LOGIN_YET_CODE,GlobalConstants.USER_LOGIN_YET_DESC)).build();
			}else{
				if(user.getPwd().equals(password)){
					boolean flag=CommonUtil.isLogonUser(String.valueOf(user.getId()));
					//OrigUser curUser=SessionUtils.getSysUserFormSession(request);
					if(flag){
						response.sendRedirect("../../../index.jsp?user=3");
						return Response.status(200).entity(new Status(GlobalConstants.USER_LOGIN_YET_CODE,GlobalConstants.USER_LOGIN_YET_DESC)).build();
					}else{
						SessionUtils.setSysUserToSession(request,user);//登录成功,放置当前的对象到session中
						SessionUtils.checkUserRepeat(request, user.getName()+"");
						response.sendRedirect("../../../manage/index.html?P=" + user.hashCode() + "&ID="+user.getName()+"&U="+user.getId()+"&Q="+user.getRemark());
						return Response.status(200).entity(user).build();//new Status(MyConstant.ONE,user)
					}
					 
					//SessionUtils.setSysUserToSession(request,user);//登录成功,放置当前的对象到session中
					//SessionUtils.checkUserRepeat(request, user.getId()+"");
					//return Response.status(200).entity(user).build();//new Status(MyConstant.ONE,user)
					
				}else{
					response.sendRedirect("../../../index.jsp?user=2");
					return Response.status(200).entity(new Status(GlobalConstants.USER_PASSWORD_WRONG_CODE,GlobalConstants.USER_PASSWORD_WRONG_DESC)).build();
				}
			}
		}catch(Exception e){
			logger.error(e.toString());
			e.printStackTrace();  
			return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
		}
		
	}
	
	//离线线设备
		@POST 
		@Path("readcunmt")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces({MediaType.APPLICATION_JSON})	
		public Response readcunmt( ){
			try{
				Map<String,Object> map =new HashMap<>();
				List<SxxinfoVO> res = sxxservice.getmsmUNConnect(map);//查找离弦设备
				return Response.status(200).entity(res).build();//new Status(MyConstant.ONE,user)
			}catch(Exception e){
				logger.error(e.toString());
				e.printStackTrace();  
				return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			}
			
		}
		//在线线设备
		@POST 
		@Path("readoncunmtroom1")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces({MediaType.APPLICATION_JSON})	
		public Response readoncunmt( ){
			try{
				Map<String,Object> map =new HashMap<>();
				List<ModuleConfigPo> res = sxxservice.readoncunmtroom1(map);//查找离弦设备
				for (Iterator iterator = res.iterator(); iterator.hasNext();) {
					ModuleConfigPo reVO = (ModuleConfigPo) iterator.next();
					ModuleConfigPo isonvo = sxxservice.getisOnlin(reVO.getDev_id());
					
					if("1".equals(reVO.getOnline())){
						reVO.setOnline("上线");
					}else{
						reVO.setOnline("离线");
					}
					if("0".equals(reVO.getEvent())||null==reVO.getEvent()){
						reVO.setEvent("正常");
						reVO.setStatus("0");
					}else{
						int e= Integer.parseInt(reVO.getEvent());
						String event ="报警：";
						if((128&e )==128){
							event=event+"通讯故障、";
							reVO.setStatus("1");
						}
						if((1&e )==1){
							event=event+"出现废品、";
							reVO.setStatus("1");
						}
						if((2&e )==2){
							event=event+"恒温设备故障、";
							reVO.setStatus("1");
						}
						if((4&e )==4){
							event=event+"高温报警、";
							reVO.setStatus("1");
						}
						if((8&e )==8){
							event=event+"出现废品、";
							reVO.setStatus("1");
						}
						if((16&e )==16){
							event=event+"电压过低、";
							reVO.setStatus("1");
						}
						if((32&e )==32){
							event=event+"电压过高、";
							reVO.setStatus("1");
						}
						if((64&e )==64){
							event=event+"测试完成、";
							reVO.setStatus("1");
						}
						if("".equals(reVO.getStatus())||null==reVO.getStatus()){
							reVO.setStatus("0");
						}
						reVO.setEvent(event);
					}
					if(Integer.parseInt(isonvo.getOnline())>0){
						reVO.setEvent("通讯故障");
						reVO.setStatus("1");
						reVO.setTemp("通讯故障");
					}else{
						reVO.setTemp(isonvo.getTemp_adj());
						reVO.setLogtime(isonvo.getBatchNo());
					}
					if(null==reVO.getTemp()||"".equals(reVO.getTemp())){
						reVO.setEvent("通讯故障");
						reVO.setStatus("1");
						reVO.setTemp("通讯故障");
					}
					
				}
				System.out.println("-----------------------------------------------------1");
				return Response.status(200).entity(res).build();//new Status(MyConstant.ONE,user)
			}catch(Exception e){
				logger.error(e.toString());
				e.printStackTrace();  
				return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			}
			
		}
		//在线线设备
				@POST 
				@Path("room2readoncunmt")
				@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
				@Produces({MediaType.APPLICATION_JSON})	
				public Response room2readoncunmt( ){
					try{
						Map<String,Object> map =new HashMap<>();
						List<ModuleConfigPo> res = sxxservice.readoncunmtroom2(map);//查找离弦设备
						for (Iterator iterator = res.iterator(); iterator.hasNext();) {
							ModuleConfigPo reVO = (ModuleConfigPo) iterator.next();
							ModuleConfigPo isonvo = sxxservice.getisOnlin(reVO.getDev_id());
							
							if("1".equals(reVO.getOnline())){
								reVO.setOnline("上线");
							}else{
								reVO.setOnline("离线");
							}
							if("0".equals(reVO.getEvent())||null==reVO.getEvent()){
								reVO.setEvent("正常");
								reVO.setStatus("0");
							}else{
								int e= Integer.parseInt(reVO.getEvent());
								String event ="报警：";
								if((128&e )==128){
									event=event+"通讯故障、";
									reVO.setStatus("1");
								}
								if((1&e )==1){
									event=event+"出现废品、";
									reVO.setStatus("1");
								}
								if((2&e )==2){
									event=event+"恒温设备故障、";
									reVO.setStatus("1");
								}
								if((4&e )==4){
									event=event+"高温报警、";
									reVO.setStatus("1");
								}
								if((8&e )==8){
									event=event+"出现废品、";
									reVO.setStatus("1");
								}
								if((16&e )==16){
									event=event+"电压过低、";
									reVO.setStatus("1");
								}
								if((32&e )==32){
									event=event+"电压过高、";
									reVO.setStatus("1");
								}
								if((64&e )==64){
									event=event+"测试完成、";
									reVO.setStatus("1");
								}
								if("".equals(reVO.getStatus())||null==reVO.getStatus()){
									reVO.setStatus("0");
								}
								reVO.setEvent(event);
							}
							if(Integer.parseInt(isonvo.getOnline())>0){
								reVO.setEvent("通讯故障");
								reVO.setStatus("1");
								reVO.setTemp("通讯故障");
							}else{
								reVO.setTemp(isonvo.getTemp_adj());
								reVO.setLogtime(isonvo.getBatchNo());
							}
							if(null==reVO.getTemp()||"".equals(reVO.getTemp())){
								reVO.setEvent("通讯故障");
								reVO.setStatus("1");
								reVO.setTemp("通讯故障");
							}
							
						}
						System.out.println("-----------------------------------------------------2");
						return Response.status(200).entity(res).build();//new Status(MyConstant.ONE,user)
					}catch(Exception e){
						logger.error(e.toString());
						e.printStackTrace();  
						return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
					}
					
				}
				@POST 
				@Path("room3readoncunmt")
				@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
				@Produces({MediaType.APPLICATION_JSON})	
				public Response room3readoncunmt( ){
					try{
						Map<String,Object> map =new HashMap<>();
						List<ModuleConfigPo> res = sxxservice.readoncunmtroom3(map);//查找离弦设备
						for (Iterator iterator = res.iterator(); iterator.hasNext();) {
							ModuleConfigPo reVO = (ModuleConfigPo) iterator.next();
							ModuleConfigPo isonvo = sxxservice.getisOnlin(reVO.getDev_id());
							
							if("1".equals(reVO.getOnline())){
								reVO.setOnline("上线");
							}else{
								reVO.setOnline("离线");
							}
							if("0".equals(reVO.getEvent())||null==reVO.getEvent()){
								reVO.setEvent("正常");
								reVO.setStatus("0");
							}else{
								int e= Integer.parseInt(reVO.getEvent());
								String event ="报警：";
								if((128&e )==128){
									event=event+"通讯故障、";
									reVO.setStatus("1");
								}
								if((1&e )==1){
									event=event+"出现废品、";
									reVO.setStatus("1");
								}
								if((2&e )==2){
									event=event+"恒温设备故障、";
									reVO.setStatus("1");
								}
								if((4&e )==4){
									event=event+"高温报警、";
									reVO.setStatus("1");
								}
								if((8&e )==8){
									event=event+"出现废品、";
									reVO.setStatus("1");
								}
								if((16&e )==16){
									event=event+"电压过低、";
									reVO.setStatus("1");
								}
								if((32&e )==32){
									event=event+"电压过高、";
									reVO.setStatus("1");
								}
								if((64&e )==64){
									event=event+"测试完成、";
									reVO.setStatus("1");
								}
								if("".equals(reVO.getStatus())||null==reVO.getStatus()){
									reVO.setStatus("0");
								}
								reVO.setEvent(event);
							}
							if(Integer.parseInt(isonvo.getOnline())>0){
								reVO.setEvent("通讯故障");
								reVO.setStatus("1");
								reVO.setTemp("通讯故障");
							}else{
								reVO.setTemp(isonvo.getTemp_adj());
								reVO.setLogtime(isonvo.getBatchNo());
							}
							if(null==reVO.getTemp()||"".equals(reVO.getTemp())){
								reVO.setEvent("通讯故障");
								reVO.setStatus("1");
								reVO.setTemp("通讯故障");
							}
							
						}
						System.out.println("-----------------------------------------------------3");
						return Response.status(200).entity(res).build();//new Status(MyConstant.ONE,user)
					}catch(Exception e){
						logger.error(e.toString());
						e.printStackTrace();  
						return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
					}
					
				}
		//在线线设备
		@POST 
		@Path("channel")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces({MediaType.APPLICATION_JSON})	
		public Response channel(@FormParam("XH") String id){
			try{
				Map<String,Object> map =new HashMap<>();
				map.put("id", id);
				List<ChanlesPo> res = new ArrayList<ChanlesPo>();
				List<ChanlesPo> resonlin = chanleservice.getisOnline(map);//判断上线状态
				List<ChanlesPo> reson3mm = chanleservice.getisreson3mm(map);//判断三分钟内有无连接数据状态
				List<AlarmConfigPo> resfp= alarmConfigService.getBatchFpSByXH(map);//获取当前箱号下所有正在运行的批次号废品产生情况及最后一次废品产生的时间
				List<ChanlesPo> resonfp = new ArrayList<ChanlesPo>();
				/*
				 * 判断是否在线不在线直接把状态变成通讯故障（1）
				 */
				for (Iterator iterator = resonlin.iterator(); iterator
						.hasNext();) {
					ChanlesPo chanlesPo = (ChanlesPo) iterator.next();
					if(chanlesPo.getEvent().equals("0")){
						chanlesPo.setEvent("128");
						res.add(chanlesPo);
					}
					
				}
				for (Iterator iterator = resfp.iterator(); iterator.hasNext();) {
					AlarmConfigPo alarmConfigPo = (AlarmConfigPo) iterator
							.next();
					String h = alarmConfigPo.getFp_time();
					String timer = "";
					if(null!=h&&!"".equals(h)){
						int hh = (int)Math.floor(Float.parseFloat(h));
						int mm = (int)Math.floor((Float.parseFloat(h)-Math.floor(Float.parseFloat(h)))*60);
						String mi = "";
						if(mm>=10){
							mi=mm+"";
						}else{
							mi="0"+mm;
						}
						timer=hh+":"+mi;
						
					}
					ChanlesPo ds = new ChanlesPo();
					ds.setBatchno(alarmConfigPo.getBatchNo());
					ds.setImagid(timer);
					resonfp.add(ds);
				}
				if(res.size()<1){
					/*
					 *判断是否超过3分钟没有获得在线数据，如未获取到数据 直接将箱子状态变为离线状态
					 */
					if(reson3mm.size()>0){
						res = chanleservice.getChannelInfo(map);//查询该设备报警状态
					}else{
						ChanlesPo chanlesPo = new ChanlesPo();//3分钟内已经离线直接把在线状态改为离线
						chanlesPo.setEvent("128");
						res.add(chanlesPo);
						}
					}
					
				
				List<AlarmConfigPo> modeconfig = alarmConfigService.getmoduleconfig(id);//查找离弦设备
				List<AlarmConfigPo> modeAlarm = alarmConfigService.getmoduledata(id);//查找离弦设备
				List<AlarmConfigPo> resAlarm = alarmConfigService.getAlarmConfig(id);//当前设备数据
				AlarmConfigPo aso = new AlarmConfigPo();
				AlarmConfigPo aso1 = new AlarmConfigPo();
				AlarmConfigPo aso2 = new AlarmConfigPo();
				
				if(resAlarm.size()>0){
					aso2 = resAlarm.get(0);
				}
				if(modeconfig.size()>0){
					aso = modeconfig.get(0);
				}
				if(modeAlarm.size()>0){
					aso1 = modeAlarm.get(0);
				}
				String times ="";
				if(null!=aso2.getSj()&&!"".equals(aso2.getSj())){
				
				int time = Integer.parseInt(aso2.getSj());
				if(time>0){
					double all = (double) (Math.round(time/36)/100.0);
					int  min =  (int) Math.floor(time/3600);
					int max = (int)Math.floor((all-min)*60);
					String hour =  min>10?min+"":("0"+min);
					String mm =  max>10?max+"":("0"+max);
					 times = hour+":"+ mm;
				}else{
					 
					 times = "00:00";
				}
				
				}
				aso1.setSj(times);
				aso1.setId(aso2.getId());
				aso1.setBatchNo(aso2.getBatchNo());
				aso1.setDevices(aso2.getDevices());
				aso1.setFlag(aso.getFlag());
				List<AlarmConfigPo> resAlarms =new ArrayList<AlarmConfigPo>();
				resAlarms.add(aso1);
				Map<String,Object> ls =new HashMap<String,Object>();
				ls.put("ChanlesPo", res);
				ls.put("AlarmConfigPo", resAlarms);
				ls.put("Resonfp", resonfp);
				System.out.println("------------------"+id);
				return Response.status(200).entity(ls).build();//new Status(MyConstant.ONE,user)
			}catch(Exception e){
				logger.error(e.toString());
				e.printStackTrace();  
				return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			}
			
		}
		@GET
	    @Path("/download")
		@Produces({MediaType.APPLICATION_JSON})
	    public Response downLoadFile(@QueryParam("id") String id){
			String driverName = "net.sourceforge.jtds.jdbc.Driver"; 
	    	 //连接服务器和数据库sample 
	    	 String dbURL = "jdbc:jtds:sqlserver://localhost:50024;DatabaseName=sde"; 
	    	 String userName = "sa"; //默认用户名 
	    	String userPwd = "lp2000410"; //密码 
	    	ResultSet rs = null;
	 	    Statement stmt = null;
	 	   int len = 10 * 1024 * 1024; //定义字符数组长度
	    	Connection dbConn = null; 
	    	 try { 
	    	 Class.forName(driverName); 
	    	 dbConn = DriverManager.getConnection(dbURL, userName, userPwd); 
	    	 System.out.println("Connection Successful!"); //如果连接成功 控制台输出
	    	 stmt = dbConn.createStatement();
	    	 String SQLString = "select * from rastermeta where minimap is not null";
	         rs = stmt.executeQuery(SQLString);
	         InputStream in = null; //定义输入流
		            if (rs.next()) {
		                in = rs.getBinaryStream("minimap");
		                response.reset(); //返回在流中被标记过的位置
		                response.setContentType("image/jpg"); //或gif等
		                // int len=in.available();//得到文件大小
		                OutputStream toClient = response.getOutputStream();
		                byte[] P_Buf = new byte[len];
		                int i;
		                while ((i = in.read(P_Buf)) != -1) {
		                    toClient.write(P_Buf, 0, i);
		                }
		                in.close();
		                toClient.flush(); //强制清出缓冲区
		                toClient.close();
		            }
		            rs.close();
		     
	    	} catch (Exception e) { 
	    	 e.printStackTrace(); 
	    	 }
	    	 finally{
	    		 try {
					dbConn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	 }
	    	 
			return null; 
			 
		 }
		@POST 
		@Path("getwarring")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces({MediaType.APPLICATION_JSON})	
		public Response getWarring( ){
			try{
				Map<String,Object> map =new HashMap<>();
				List<ModuleConfigPo> res1 = sxxservice.readoncunmtroom1(map);//查找离弦设备
				List<ModuleConfigPo> res2 = sxxservice.readoncunmtroom2(map);//查找离弦设备
				List<ModuleConfigPo> res3 = sxxservice.readoncunmtroom3(map);//查找离弦设备
				Map resmap = new HashMap<String,String>();
				int isred = 0;
				for (Iterator iterator = res1.iterator(); iterator.hasNext();) {
					ModuleConfigPo reVO = (ModuleConfigPo) iterator.next();
					ModuleConfigPo isonvo = sxxservice.getisOnlin(reVO.getDev_id());
					if(Integer.parseInt(isonvo.getOnline())>0){
						isred = 1;
						break;
					}
					if(!"0".equals(reVO.getEvent())&&null!=reVO.getEvent()){
						isred = 1;
						break;
					}
					if(null==reVO.getTemp()||"".equals(reVO.getTemp())){
						isred = 1;
						break;
					}
					
				}
				if(isred == 0){
					resmap.put("room1", "#000000");
				}else{
					resmap.put("room1", "#F00");
				}
				isred = 0;
				for (Iterator iterator = res2.iterator(); iterator.hasNext();) {
					ModuleConfigPo reVO = (ModuleConfigPo) iterator.next();
					ModuleConfigPo isonvo = sxxservice.getisOnlin(reVO.getDev_id());
					if(Integer.parseInt(isonvo.getOnline())>0){
						isred = 1;
						break;
					}
					if(!"0".equals(reVO.getEvent())&&null!=reVO.getEvent()){
						isred = 1;
						break;
					}
					if(null==reVO.getTemp()||"".equals(reVO.getTemp())){
						isred = 1;
						break;
					}
					
				}
				if(isred == 0){
					resmap.put("room2", "#000000");
				}else{
					resmap.put("room2", "#F00");
				}
				isred = 0;
				for (Iterator iterator = res3.iterator(); iterator.hasNext();) {
					ModuleConfigPo reVO = (ModuleConfigPo) iterator.next();
					ModuleConfigPo isonvo = sxxservice.getisOnlin(reVO.getDev_id());
					if(Integer.parseInt(isonvo.getOnline())>0){
						isred = 1;
						break;
					}
					if(!"0".equals(reVO.getEvent())&&null!=reVO.getEvent()){
						isred = 1;
						break;
					}
					if(null==reVO.getTemp()||"".equals(reVO.getTemp())){
						isred = 1;
						break;
					}
					
				}
				if(isred == 0){
					resmap.put("room3", "#000000");
				}else{
					resmap.put("room3", "#F00");
				}
				System.out.println("-----------------------------------------------------3");
				return Response.status(200).entity(resmap).build();//new Status(MyConstant.ONE,user)
			}catch(Exception e){
				logger.error(e.toString());
				e.printStackTrace();  
				return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			}
			
		}
		@POST 
		@Path("lefton")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces({MediaType.APPLICATION_JSON})	
		public Response leftOn( ){
			try{
				int res1 = sxxservice.leftOn();//查找离弦设备
				return Response.status(200).entity(res1).build();//new Status(MyConstant.ONE,user)
			}catch(Exception e){
				logger.error(e.toString());
				e.printStackTrace();  
				return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			}
			
		}
		@POST 
		@Path("leftoff")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces({MediaType.APPLICATION_JSON})	
		public Response leftoff( ){
			try{
				int res1 = sxxservice.leftoff();//查找离弦设备
				return Response.status(200).entity(res1).build();//new Status(MyConstant.ONE,user)
			}catch(Exception e){
				logger.error(e.toString());
				e.printStackTrace();  
				return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			}
			
		}
		@POST 
		@Path("righton")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces({MediaType.APPLICATION_JSON})	
		public Response righton( ){
			try{
				int res1 = sxxservice.righton();//查找离弦设备
				return Response.status(200).entity(res1).build();//new Status(MyConstant.ONE,user)
			}catch(Exception e){
				logger.error(e.toString());
				e.printStackTrace();  
				return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			}
			
		}
		@POST 
		@Path("righoff")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces({MediaType.APPLICATION_JSON})	
		public Response righoff( ){
			try{
				int res1 = sxxservice.righoff();//查找离弦设备
				return Response.status(200).entity(res1).build();//new Status(MyConstant.ONE,user)
			}catch(Exception e){
				logger.error(e.toString());
				e.printStackTrace();  
				return Response.status(500).entity(new Status(GlobalConstants.TWO,GlobalConstants.SERVICE_EXCEPTION)).build();
			}
			
		}
	
}
