package com.cdatabank.shuhuibao.core.service;


import java.util.List;
import java.util.Map;

import com.cdatabank.shuhuibao.core.po.ChanlesPo;
import com.cdatabank.shuhuibao.core.service.base.IBaseService;
import com.cdatabank.shuhuibao.core.vo.SxxinfoVO;

public interface IChanleservice  extends IBaseService<ChanlesPo>{

	List<ChanlesPo> getChannelInfo(Map<String, Object> map);

	List<ChanlesPo> getisOnline(Map<String, Object> map);

	List<ChanlesPo> getisreson3mm(Map<String, Object> map);


}
