package com.landcoder.common.freemarker;

import java.io.IOException;
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
 * 获取字典名称
 * @author landcoder
 * @company oschina
 * 用法：[@dict_name pid=11 dictVal=1/]
 */
@Component(value = "dict_name")
public class DictNameDirective implements TemplateDirectiveModel {

	@Resource
	private CDictionaryService cDictionaryService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CDictionaryQuery q = new CDictionaryQuery();
		q.setPid(DirectiveUtils.getInt("pid", params));
		q.setDictVal(DirectiveUtils.getString("dictVal", params));
		q.setId(DirectiveUtils.getInt("id", params));
		CDictionary cDictionary = cDictionaryService.findOne(q);
		env.getOut().append(cDictionary.getDictName());
	}
}
