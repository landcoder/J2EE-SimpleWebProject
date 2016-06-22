package com.landcoder.sys.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.landcoder.framework.base.Page;
import com.landcoder.framework.base.VEntity;
import com.landcoder.sys.model.CDictionary;
import com.landcoder.sys.query.CDictionaryQuery;

/**
 * 数据字典业务层
 * @author landcoder
 * @company oschina
 */
public interface CDictionaryService {

	public CDictionary getById(Integer id) throws DataAccessException;
	
	public void saveOrUpdate(CDictionary entity) throws DataAccessException;
	
	public void save(CDictionary entity) throws DataAccessException;
	
	public void removeById(Integer id) throws DataAccessException;
	
	public void removeById(Integer id, CDictionary entity) throws DataAccessException;
	
	public void update(CDictionary entity) throws DataAccessException;

	public Page findPage(VEntity p) throws DataAccessException;

	public List<CDictionary> find(VEntity p) throws DataAccessException;
	
	public List<CDictionary> findPartlist(VEntity p) throws DataAccessException;

	public CDictionary findOne(CDictionaryQuery q) throws DataAccessException;
}
