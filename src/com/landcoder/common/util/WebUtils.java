package com.landcoder.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.landcoder.common.web.ip.SinaIPModel;

/**
 * web工具类
 * @author landcoder
 * @company oschina
 */
public class WebUtils {
	
	/**
	 * 清除Cookie.
	 * 
	 * @param response
	 * @param name
	 */
	public static void clearCookie(HttpServletResponse response, HttpServletRequest request, String name) {
		Cookie cookie = new Cookie(name, null);
		String path = request.getContextPath();
		cookie.setPath(path);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/**
	 * 获得请求中的cookie.
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		return org.springframework.web.util.WebUtils.getCookie(request, name);
	}

	/**
	 * 通过HttpServletRequest返回IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
	}
	
	/**
	 * 根据ip地址找到对应的地址信息
	 * @param ip ip地址
	 * @param encode 编码格式
	 * @return
	 */
	public static String getAddrByIp(String ip, String encode) {
		try {
		    URL url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=" + ip); 
		    URLConnection conn = url.openConnection(); 
		    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), encode)); 
		    String line = null; 
		    StringBuffer result = new StringBuffer(); 
		    while((line = reader.readLine()) != null) { 
		    	result.append(line); 
		    } 
		    reader.close();
		    if ("-3".equals(result.toString()) || "-2".equals(result.toString())) { //本机登陆ip地址错误
		    	return "暂无地址数据";
		    }
		    String json = result.substring(result.indexOf("{"));
		    SinaIPModel ipmodel = (SinaIPModel) JSONObject.toBean(JSONObject.fromObject(json), SinaIPModel.class);
		    if (ipmodel.getRet() == -1) { //局域网ip地址错误
		    	return "暂无地址数据";
		    }
		    return ipmodel.getProvince() + "省" + ipmodel.getCity() + "市";
		} catch (Exception e) {
			e.printStackTrace();
			return "暂无地址数据";
		}
	}
  
    /**  
     * 基本功能：过滤指定标签  
     * <p> tag为null时过滤所有标签,保留标签中内容
     * @param str
     * @param tag 指定标签 
     * @return String
     */  
    public static String filterHtmlTag(String str, String tag) {
    	String regxp = "<"+tag+"[^>]*?>[\\s\\S]*?<\\/"+tag+">";
		if (tag == null) {
			regxp = "<[^>]+>";
		}
        Pattern pattern = Pattern.compile(regxp);
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("");
    }
    
    /**
     * 生成手机验证码
     * @return
     */
    public static String getRandomCode() {
    	 Random random = new Random();
    	 int i = 0;
    	 String str = "";
    	 while(i < 6) {
    		 i++;
    		 str += Integer.toString(random.nextInt(10));
    	 }
    	 return str;
    }
}
