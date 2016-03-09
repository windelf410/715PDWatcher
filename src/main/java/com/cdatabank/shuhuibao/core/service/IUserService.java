package com.cdatabank.shuhuibao.core.service;


import com.cdatabank.shuhuibao.core.po.OrigUser;
import com.cdatabank.shuhuibao.core.service.base.IBaseService;

public interface IUserService extends IBaseService<OrigUser> {

	public OrigUser getUserByName(String name);
}
