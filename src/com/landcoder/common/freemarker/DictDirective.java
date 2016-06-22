package com.landcoder.common.freemarker;

import static com.landcoder.common.freemarker.DirectiveUtils.OUT_LIST;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.landcoder.sys.model.CDictionary;
import com.landcoder.sys.query.CDictionaryQuery;
import com.landcoder.sys.service.CDictionaryService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 数据字典
 * @author landcoder
 * @company oschina
 * 用法如下：
 * [@dictionary pid=2]
 * [#list tag_list as d]
 * ${d.dictVal}   ${d.dictName}
 * [/#list]
 * [/@dictionary]
 */
@Component(value="dictionary")
public class DictDirective implements TemplateDirectiveModel {

	@Resource
	private CDictionaryService cDictionaryService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CDictionaryQuery q = new CDictionaryQuery();
		q.setPid(DirectiveUtils.getInt("pid", params));
		q.setPageSize(DirectiveUtils.getInt("count", params));
		q.setSortColumns(DirectiveUtils.getString("orderBy", params));
		List<CDictionary> list = cDictionaryService.find(q);
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(list));
		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
}
