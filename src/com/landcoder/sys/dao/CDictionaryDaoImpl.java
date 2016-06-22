package com.landcoder.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.landcoder.framework.base.BaseIbatis3Dao;
import com.landcoder.framework.base.Page;
import com.landcoder.framework.base.VEntity;
import com.landcoder.sys.model.CDictionary;
import com.landcoder.sys.query.CDictionaryQuery;

/**
 * 数据字典数据持久层
 * @author landcoder
 * @company oschina
 */
@Repository(value="cDictionaryDao")
public class CDictionaryDaoImpl extends BaseIbatis3Dao<CDictionary,Integer>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "com.landcoder.sys.model.CDictionary.";
	}
	
	public void saveOrUpdate(CDictionary entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public List<CDictionary> findList(VEntity p) {
		return getSqlSession().selectList(getIbatisMapperNamesapce()+"findlist",p);
	}
	
	public List<CDictionary> findPartlist(VEntity p) {
		return selectList("findPartlist",p);
	}
	
	public Number findCount(VEntity p) {
		return (Number)getSqlSession().selectOne(getIbatisMapperNamesapce()+"findcount",p);
	}
	
	public Page findPage(VEntity p) {
		Number totalCount = findCount(p);
		if(totalCount == null || totalCount.longValue() <= 0) {
			return new Page(1,20,0);
		}
		Page page = new Page(p.getCpage(),p.getPageSize(),totalCount.intValue());
		page.setResult(findList(p));
		return page;
	}

	public CDictionary findOne(CDictionaryQuery q) {
		return selectOne("findOne", q);
	}
}
