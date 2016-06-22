package com.landcoder.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.landcoder.framework.base.BaseService;
import com.landcoder.framework.base.IEntityDao;
import com.landcoder.framework.base.Page;
import com.landcoder.framework.base.VEntity;
import com.landcoder.sys.dao.CDictionaryDaoImpl;
import com.landcoder.sys.model.CDictionary;
import com.landcoder.sys.query.CDictionaryQuery;
import com.landcoder.sys.service.CDictionaryService;

@Service(value="cDictionaryService")
public class CDictionaryServiceImpl extends BaseService<CDictionary,Integer> implements CDictionaryService {

	@Resource
	private CDictionaryDaoImpl cDictionaryDao;

	public IEntityDao<CDictionary,Integer> getEntityDao() {
		return this.cDictionaryDao;
	}

	public Page findPage(VEntity p) throws DataAccessException {
		return cDictionaryDao.findPage(p);
	}

	public List<CDictionary> find(VEntity p) throws DataAccessException {
		return cDictionaryDao.findList(p);
	}
	
	public List<CDictionary> findPartlist(VEntity p) throws DataAccessException {
		return cDictionaryDao.findPartlist(p);
	}
	
	public void saveOrUpdate(CDictionary entity) throws DataAccessException{
		cDictionaryDao.saveOrUpdate(entity);
	}
	
	public void removeById(Integer id,CDictionary entity) throws DataAccessException{
		cDictionaryDao.deleteById(id);
	}

	@Override
	public CDictionary findOne(CDictionaryQuery q) throws DataAccessException {
		return cDictionaryDao.findOne(q);
	}
}
