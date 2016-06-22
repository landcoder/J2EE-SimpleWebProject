package com.landcoder.sys.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.landcoder.framework.base.BaseAction;

/**
 * 主页action
 * @author landcoder
 * @company oschina
 */
@Controller
public class IndexAct extends BaseAction {

	/**
	 * 返回项目主页
	 * @return
	 */
	@RequestMapping("index.html")
	public String index() {
		return result("index.html");
	}

}
