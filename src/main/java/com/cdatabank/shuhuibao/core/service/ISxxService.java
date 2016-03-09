package com.cdatabank.shuhuibao.core.service;

import java.util.List;
import java.util.Map;

import com.cdatabank.shuhuibao.core.po.ModuleConfigPo;
import com.cdatabank.shuhuibao.core.po.OrigUser;
import com.cdatabank.shuhuibao.core.service.base.IBaseService;
import com.cdatabank.shuhuibao.core.vo.SxxinfoVO;

public interface ISxxService extends IBaseService<ModuleConfigPo>{

	List<SxxinfoVO> getmsmUNConnect(Map map);

	List<ModuleConfigPo> getmoduleconfigpage();

	List<ModuleConfigPo> readoncunmtroom1(Map<String, Object> map);

	List<ModuleConfigPo> readoncunmtroom2(Map<String, Object> map);

	List<ModuleConfigPo> readoncunmtroom3(Map<String, Object> map);

	int leftOn();

	int leftoff();

	int righton();

	int righoff();

	ModuleConfigPo getisOnlin(String dev_id);

}
