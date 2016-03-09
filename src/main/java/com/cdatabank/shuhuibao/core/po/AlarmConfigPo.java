package com.cdatabank.shuhuibao.core.po;

import java.io.Serializable;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdatabank.shuhuibao.core.vo.PageParam;


@XmlRootElement
public class AlarmConfigPo extends PageParam implements Serializable{

	@FormParam("id")
	private int id;
	
	@FormParam("batchNo")
	private String batchNo;
	
	@FormParam("devices")
	private String devices;
	
	@FormParam("dy")
	private String dy;
	
	@FormParam("dy_low")
	private String dy_low;
	
	@FormParam("dy_hi")
	private String dy_hi;
	
	@FormParam("dywc")
	private String dywc;
	
	@FormParam("wd")
	private String wd;
	
	@FormParam("wdwc")
	private String wdwc;
	
	@FormParam("wd_low")
	private String wd_low;
	
	@FormParam("wd_hi")
	private String wd_hi;
	
	@FormParam("start")
	private String start;
	
	@FormParam("sj")
	private String sj;
	
	@FormParam("endTime")
	private String endTime;
	
	@FormParam("status")
	private String status;
	
	@FormParam("fp")
	private String fp;
	
	@FormParam("fpcs")
	private String fpcs;
	
	@FormParam("time")
	private String time;
	
	@FormParam("user")
	private String user;
	
	@FormParam("flag")
	private String flag;
	
	@FormParam("event")
	private String event;
	
	@FormParam("remark")
	private String remark;	
	
	@FormParam("warcount")
	private String warcount;	
	
	@FormParam("ad_1")
	private String ad_1;
	@FormParam("ad_2")
	private String ad_2;
	@FormParam("ad_3")
	private String ad_3;
	@FormParam("ad_4")
	private String ad_4;
	@FormParam("ad_5")
	private String ad_5;
	@FormParam("ad_6")
	private String ad_6;
	@FormParam("ad_7")
	private String ad_7;
	@FormParam("ad_8")
	private String ad_8;
	@FormParam("ad_9")
	private String ad_9;
	@FormParam("ad_10")
	private String ad_10;
	@FormParam("ad_11")
	private String ad_11;
	@FormParam("ad_12")
	private String ad_12;
	@FormParam("ad_13")
	private String ad_13;
	@FormParam("ad_14")
	private String ad_14;
	@FormParam("ad_15")
	private String ad_15;
	@FormParam("ad_16")
	private String ad_16;
	@FormParam("ad_17")
	private String ad_17;
	@FormParam("ad_18")
	private String ad_18;
	@FormParam("ad_19")
	private String ad_19;
	@FormParam("ad_20")
	private String ad_20;
	@FormParam("ad_21")
	private String ad_21;
	
	@FormParam("xh")
	private String xh;
	
	@FormParam("xhname")
	private String xhname;
	
	@FormParam("page")
	private int page;
	
	@FormParam("rows")
	private int rows;
	
	@FormParam("fp_time")
	private String fp_time;
	
	
	
	public String getFp_time() {
		return fp_time;
	}

	public void setFp_time(String fp_time) {
		this.fp_time = fp_time;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFpcs() {
		return fpcs;
	}

	public void setFpcs(String fpcs) {
		this.fpcs = fpcs;
	}

	public String getWd_low() {
		return wd_low;
	}

	public void setWd_low(String wd_low) {
		this.wd_low = wd_low;
	}

	public String getWd_hi() {
		return wd_hi;
	}

	public void setWd_hi(String wd_hi) {
		this.wd_hi = wd_hi;
	}

	public String getXhname() {
		return xhname;
	}

	public void setXhname(String xhname) {
		this.xhname = xhname;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getWarcount() {
		return warcount;
	}

	public void setWarcount(String warcount) {
		this.warcount = warcount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAd_1() {
		return ad_1;
	}

	public void setAd_1(String ad_1) {
		this.ad_1 = ad_1;
	}

	public String getAd_2() {
		return ad_2;
	}

	public void setAd_2(String ad_2) {
		this.ad_2 = ad_2;
	}

	public String getAd_3() {
		return ad_3;
	}

	public void setAd_3(String ad_3) {
		this.ad_3 = ad_3;
	}

	public String getAd_4() {
		return ad_4;
	}

	public void setAd_4(String ad_4) {
		this.ad_4 = ad_4;
	}

	public String getAd_5() {
		return ad_5;
	}

	public void setAd_5(String ad_5) {
		this.ad_5 = ad_5;
	}

	public String getAd_6() {
		return ad_6;
	}

	public void setAd_6(String ad_6) {
		this.ad_6 = ad_6;
	}

	public String getAd_7() {
		return ad_7;
	}

	public void setAd_7(String ad_7) {
		this.ad_7 = ad_7;
	}

	public String getAd_8() {
		return ad_8;
	}

	public void setAd_8(String ad_8) {
		this.ad_8 = ad_8;
	}

	public String getAd_9() {
		return ad_9;
	}

	public void setAd_9(String ad_9) {
		this.ad_9 = ad_9;
	}

	public String getAd_10() {
		return ad_10;
	}

	public void setAd_10(String ad_10) {
		this.ad_10 = ad_10;
	}

	public String getAd_11() {
		return ad_11;
	}

	public void setAd_11(String ad_11) {
		this.ad_11 = ad_11;
	}

	public String getAd_12() {
		return ad_12;
	}

	public void setAd_12(String ad_12) {
		this.ad_12 = ad_12;
	}

	public String getAd_13() {
		return ad_13;
	}

	public void setAd_13(String ad_13) {
		this.ad_13 = ad_13;
	}

	public String getAd_14() {
		return ad_14;
	}

	public void setAd_14(String ad_14) {
		this.ad_14 = ad_14;
	}

	public String getAd_15() {
		return ad_15;
	}

	public void setAd_15(String ad_15) {
		this.ad_15 = ad_15;
	}

	public String getAd_16() {
		return ad_16;
	}

	public void setAd_16(String ad_16) {
		this.ad_16 = ad_16;
	}

	public String getAd_17() {
		return ad_17;
	}

	public void setAd_17(String ad_17) {
		this.ad_17 = ad_17;
	}

	public String getAd_18() {
		return ad_18;
	}

	public void setAd_18(String ad_18) {
		this.ad_18 = ad_18;
	}

	public String getAd_19() {
		return ad_19;
	}

	public void setAd_19(String ad_19) {
		this.ad_19 = ad_19;
	}

	public String getAd_20() {
		return ad_20;
	}

	public void setAd_20(String ad_20) {
		this.ad_20 = ad_20;
	}

	public String getAd_21() {
		return ad_21;
	}

	public void setAd_21(String ad_21) {
		this.ad_21 = ad_21;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getDevices() {
		return devices;
	}

	public void setDevices(String devices) {
		this.devices = devices;
	}

	public String getDy() {
		return dy;
	}

	public void setDy(String dy) {
		this.dy = dy;
	}

	public String getDywc() {
		return dywc;
	}

	public void setDywc(String dywc) {
		this.dywc = dywc;
	}

	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public String getWdwc() {
		return wdwc;
	}

	public void setWdwc(String wdwc) {
		this.wdwc = wdwc;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFp() {
		return fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDy_low() {
		return dy_low;
	}

	public void setDy_low(String dy_low) {
		this.dy_low = dy_low;
	}

	public String getDy_hi() {
		return dy_hi;
	}

	public void setDy_hi(String dy_hi) {
		this.dy_hi = dy_hi;
	}
	
	
	

}
