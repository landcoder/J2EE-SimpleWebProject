package com.landcoder.framework.base;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;

/**
 * dao接口，设计模式：调度器
 * @author landcoder
 * @company oschina
 * @param <E>
 * @param <PK>
 */
public interface IEntityDao <E,PK extends Serializable>{

	/**
	 * 根据主键获取一条数据
	 * @param id 主键
	 * @return
	 * @throws DataAccessException
	 */
	public E getById(PK id) throws DataAccessException;
	
	/**
	 * 删除数据
	 * @param id 主键
	 * @throws DataAccessException
	 */
	public void deleteById(PK id) throws DataAccessException;
	
	/**
	 * 插入数据
	 * @param entity 插入的实体类
	 * @throws DataAccessException
	 */
	public void save(E entity) throws DataAccessException;
	
	/**
	 * 更新数据
	 * @param entity 更新的实体类
	 * @throws DataAccessException
	 */
	public void update(E entity) throws DataAccessException;

	/**
	 * 根据id检查是否插入或是更新数据
	 * @param entity 对应的实体类
	 * @throws DataAccessException
	 */
	public void saveOrUpdate(E entity) throws DataAccessException;
	
}
