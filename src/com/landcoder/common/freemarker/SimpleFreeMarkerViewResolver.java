package com.landcoder.common.freemarker;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.stereotype.Component;

/**
 * ViewResolver for SimpleFreeMarkerView
 * 
 * Override buildView, if viewName start with / , then ignore prefix.
 */
@Component(value="freemarkerViewResolver")
public class SimpleFreeMarkerViewResolver extends AbstractTemplateViewResolver {
	
	/**
	 * Set default viewClass
	 */
	public SimpleFreeMarkerViewResolver() {
		setViewClass(SimpleFreeMarkerView.class);
		setContentType("text/html; charset=UTF-8");
		setExposeRequestAttributes(false);
		setExposeSessionAttributes(false);
		setExposeSpringMacroHelpers(true);
	}

	/**
	 * if viewName start with / , then ignore prefix.
	 */
	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		AbstractUrlBasedView view = super.buildView(viewName);
		// start with / ignore prefix
		if (viewName.startsWith("/")) {
			view.setUrl(viewName + getSuffix());
		}
		return view;
	}
}
