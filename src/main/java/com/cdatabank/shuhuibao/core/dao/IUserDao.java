package com.cdatabank.shuhuibao.core.dao;


import com.cdatabank.shuhuibao.core.dao.base.IBaseDao;
import com.cdatabank.shuhuibao.core.po.OrigUser;

public interface IUserDao extends IBaseDao<OrigUser>{

	public OrigUser getObjectByName(String name);

	public int getSizeall();
}
