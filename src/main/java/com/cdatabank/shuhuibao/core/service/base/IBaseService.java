package com.cdatabank.shuhuibao.core.service.base;

import java.io.Serializable;
import java.util.List;

import com.cdatabank.shuhuibao.core.vo.PageList;

public interface IBaseService<T> {

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
	 * @throws Exception 
	 */
	public int deleteObjects(String ids) ;
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
	public List<T> getAll();
	
	/**
	 * 返回表的记录数
	 * @return
	 */
	public int getSize(T entity);
	
	/**
	 * 分页查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public PageList<T> getPage(T entity);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public T getObjectById(String id);
}
