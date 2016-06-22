package com.landcoder.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Property工具类
 * @author landcoder
 * @company oschina
 */
public class PropertyUtils extends PropertyPlaceholderConfigurer {

	private static Properties properties;
	private static PropertyUtils instance;
	
	public static PropertyUtils getInstance(){
		if(instance == null){
			instance = (PropertyUtils)ApplicationContextHolder.getBean("propertyConfigurer");
		}
		return instance;
	}
	
	@Override
	protected void loadProperties(Properties p) throws IOException {
		properties = p;
		super.loadProperties(p);
	}

	public List<String> getList(String prefix) {
		if (properties == null || prefix == null) {
			return Collections.emptyList();
		}
		List<String> list = new ArrayList<String>();
		Enumeration<?> en = properties.propertyNames();
		String key;
		while (en.hasMoreElements()) {
			key = (String) en.nextElement();
			if (key.startsWith(prefix)) {
				list.add(properties.getProperty(key));
			}
		}
		return list;
	}

	public Map<String, Object> getBeanMap(String prefix) {
		List<String> keyList = getList(prefix);
		if (keyList.isEmpty()) {
			return Collections.emptyMap();
		}
		Map<String, Object> resultMap = new HashMap<String, Object>(keyList.size());
		for (String entry : keyList) {
			resultMap.put(entry, ApplicationContextHolder.getBean(entry, Object.class));
		}
		return resultMap;
	}

	public static String getString(String key){
		return properties.getProperty(key);
	}

	public void init() throws IOException{
		mergeProperties();
	}
}