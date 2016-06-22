package com.landcoder.framework.base;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

/**
 * service基类
 * @author landcoder
 * @company oschina
 * @param <E> 实体
 * @param <PK> 主键
 */
@SuppressWarnings("unchecked")
public abstract class BaseService <E,PK extends Serializable> {
	
	protected Log log = LogFactory.getLog(getClass());

	@SuppressWarnings("rawtypes")
	protected abstract IEntityDao getEntityDao();

	/**
	 * 根据id获取一条数据
	 * @param id 主键
	 * @return
	 * @throws DataAccessException
	 */
	public E getById(PK id) throws DataAccessException{
		return (E)getEntityDao().getById(id);
	}
	
	/**
	 * 根据id检查是否插入或是更新数据
	 * @param entity 需要操作的实体类
	 * @throws DataAccessException
	 */
	public void saveOrUpdate(E entity) throws DataAccessException{
		getEntityDao().saveOrUpdate(entity);
	}
	
	/**
	 * 保存数据
	 * @param entity 保存的实体
	 * @throws DataAccessException
	 */
	public void save(E entity) throws DataAccessException{
		getEntityDao().save(entity);
	}
	
	/**
	 * 删除一条数据
	 * @param id 主键
	 * @throws DataAccessException
	 */
	public void removeById(PK id) throws DataAccessException{
		getEntityDao().deleteById(id);
	}
	
	/**
	 * 更新一条数据
	 * @param entity 需要更新的实体类
	 * @throws DataAccessException
	 */
	public void update(E entity) throws DataAccessException{
		getEntityDao().update(entity);
	}
}
