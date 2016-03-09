package com.cdatabank.shuhuibao.core.dao;

import java.util.List;
import java.util.Map;

import com.cdatabank.shuhuibao.core.dao.base.IBaseDao;
import com.cdatabank.shuhuibao.core.po.ModuleConfigPo;
import com.cdatabank.shuhuibao.core.vo.SxxinfoVO;


public interface ISxxDao extends IBaseDao<ModuleConfigPo>{

	List<SxxinfoVO> getmsmUNConnect(Map map);

	List<ModuleConfigPo> getmoduleconfigpage();

	List<ModuleConfigPo> readoncunmtroom1(Map<String, Object> map);

	List<ModuleConfigPo> readoncunmtroom2(Map<String, Object> map);

	List<ModuleConfigPo> readoncunmtroom3(Map<String, Object> map);

	int leftOn();

	int leftoff();

	int righton();

	int rightoff();

	ModuleConfigPo getisOnlin(String dev_id);

}
