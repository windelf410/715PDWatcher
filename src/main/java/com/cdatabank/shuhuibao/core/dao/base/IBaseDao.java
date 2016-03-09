package com.cdatabank.shuhuibao.core.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * 数据库操作基本类
 * @author cdsy006
 *
 * @param <T>
 */
public interface IBaseDao<T>{

	/**
	 * 增加一条数据
	 * @param entity
	 * @return
	 */
	public int addObject(T entity);
	/**
	 * 删除一条或多条数据
	 * @param ids
	 * @return
	 */
	public int deleteObjects(Serializable []ids);
	/**
	 * 修改一条数据
	 * @param entity
	 * @return
	 */
	public int updateObject(T entity);
	
	/**
	 * 查询所有数据
	 * @return 
	 */
	public List<T> getAll(T entity);
	
	/**
	 * 返回表的记录数
	 * @return
	 */
	public int getSize(T entity);
	
	/**
	 * 分页查询
	 * @param entity
	 * @return
	 */
	public List<T> getPage(T entity);
	
	/**
	 * 根据id查询单条记录
	 * @param id
	 * @return
	 */
	public T getObjectById(Serializable id);
	
}
