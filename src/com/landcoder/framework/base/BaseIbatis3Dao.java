package com.landcoder.framework.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * dao基类
 * @author landcoder
 * @company oschina
 * @param <E> 实体
 * @param <PK> 主键
 */
public abstract class BaseIbatis3Dao<E,PK extends Serializable> extends SqlSessionDaoSupport implements IEntityDao<E,PK> {
    protected final Log log = LogFactory.getLog(getClass());

    public abstract String getIbatisMapperNamesapce();
    
    public E getById(PK primaryKey) {
    	return getSqlSession().selectOne(getFindByPrimaryKeyStatement(), primaryKey);
    }
    
	public void deleteById(PK id) {
		getSqlSession().delete(getDeleteStatement(), id);
	}
	
    public void save(E entity) {
		getSqlSession().insert(getInsertStatement(), entity);    	
    }
    
	public void update(E entity) {
		getSqlSession().update(getUpdateStatement(), entity);
	}
    
	protected void update(String sqlId, E entity) {
		getSqlSession().update(getIbatisMapperNamesapce()+sqlId, entity);
	}
    
	protected String getFindByPrimaryKeyStatement() {
        return getIbatisMapperNamesapce()+"getById";
    }

	protected String getInsertStatement() {
        return getIbatisMapperNamesapce()+"insert";
    }

	protected String getUpdateStatement() {
    	return getIbatisMapperNamesapce()+"update";
    }

	protected String getDeleteStatement() {
    	return getIbatisMapperNamesapce()+"delete";
    }

	protected <T> T selectOne(String sqlId, Object args) {
    	return getSqlSession().selectOne(getIbatisMapperNamesapce()+sqlId, args);
    }

	protected <T> List<T> selectList(String sqlId, Object args) {
    	return getSqlSession().selectList(getIbatisMapperNamesapce()+sqlId, args);
    }

	protected <K, V> Map<K,V> selectMap(String sqlId, String args) {
    	return getSqlSession().selectMap(getIbatisMapperNamesapce()+sqlId, args);
    }

	protected <K, V> Map<K,V> selectMap(String sqlId, Object arg1, String arg2) {
    	return getSqlSession().selectMap(getIbatisMapperNamesapce()+sqlId, arg1, arg2);
    }
}
