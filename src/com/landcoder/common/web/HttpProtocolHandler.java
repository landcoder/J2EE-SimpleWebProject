package com.landcoder.common.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
import org.apache.commons.lang.StringUtils;

import com.landcoder.common.web.httpClient.HttpRequest;
import com.landcoder.common.web.httpClient.HttpResponse;
import com.landcoder.common.web.httpClient.HttpResultType;

/**
 * 
 * @author landcoder
 * @company oschina
 */
public class HttpProtocolHandler {

    private static String DEFAULT_CHARSET = "GB2312";

    /** 连接超时时间，由bean factory设置，缺省为8秒钟 */
    private int defaultConnectionTimeout = 8000;

    /** 回应超时时间, 由bean factory设置，缺省为30秒钟 */
    private int defaultSoTimeout = 30000;

    /** 闲置连接超时时间, 由bean factory设置，缺省为60秒钟 */
    private int defaultIdleConnTimeout = 60000;

    private int defaultMaxConnPerHost = 30;

    private int defaultMaxTotalConn = 80;

    /** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：1秒*/
    private static final long defaultHttpConnectionManagerTimeout = 3 * 1000;

    /**
     * HTTP连接管理器，该连接管理器必须是线程安全的.
     */
    private HttpConnectionManager connectionManager;

    private static HttpProtocolHandler httpProtocolHandler = new HttpProtocolHandler();

    /**
     * 工厂方法
     * 
     * @return
     */
    public static HttpProtocolHandler getInstance() {
        return httpProtocolHandler;
    }

    /**
     * 私有的构造方法
     */
    private HttpProtocolHandler() {
        // 创建一个线程安全的HTTP连接池
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
        connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);
        IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
        ict.addConnectionManager(connectionManager);
        ict.setConnectionTimeout(defaultIdleConnTimeout);
        ict.start();
    }

    /**
     * 执行Http请求
     * 
     * @param uri 请求地址
     * @param param 参数
     * @return 
     * @throws HttpException, IOException 
     */
    public String execute(String uri, Map<String, Object> param, String method) throws HttpException, IOException {
    	return execute(uri, param, method, null);
    }

    /**
     * 执行Http请求
     * 
     * @param uri 请求地址
     * @param param 参数
     * @return 
     * @throws HttpException, IOException 
     */
    public String execute(String uri, Map<String, Object> param, String method, String encode) throws HttpException, IOException {
    	if(encode == null){
    		encode = DEFAULT_CHARSET;
    	}
        HttpClient httpclient = new HttpClient(connectionManager);
        // 设置连接超时
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
        // 设置回应超时
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);
        // 设置等待ConnectionManager释放connection的时间
        httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);
        HttpMethod http = null;
        if(StringUtils.isEmpty(method)){
        	method = "post";
        }
        //get模式
        if ("get".equals(method.toLowerCase())) {
            http = new GetMethod(urlADDparam(uri,param,encode));
            http.getParams().setCredentialCharset(encode);
        }else {
        	//post模式
            NameValuePair[] parameters = toNameValuePairs(param);
            http = new PostMethod(uri);
            http.getParams().setCredentialCharset(encode);
            if (parameters!=null) {
				// 设置请求体
				((PostMethod) http).addParameters(toNameValuePairs(param));
			}
        }
        // 设置Http Header中的User-Agent属性
        http.addRequestHeader("User-Agent", "Mozilla/4.0");
        http.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,encode);
        try {
            httpclient.executeMethod(http);
            return http.getResponseBodyAsString();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            http.releaseConnection();
        }
        return null;
    }

    private String urlADDparam(String uri, Map<String, Object> param, String encode) throws UnsupportedEncodingException {
		if(param == null || param.size()==0){
			return uri;
		}
		StringBuffer pStr = new StringBuffer();
		for (String k : param.keySet()) {
            pStr.append("&"+k+"="+URLEncoder.encode(param.get(k).toString(),encode));
        }
		return uri+"?"+pStr.substring(1);
	}

	/**
     * 将Map转变为NameValuePair
     * 
     * @param nameValues
     * @return
     */
    protected NameValuePair[] toNameValuePairs(Map<String,Object> p) {
        if (p == null || p.size() == 0) {
            return null;
        }
        List<NameValuePair> buffer = new ArrayList<NameValuePair>();
        NameValuePair[] array = new NameValuePair[p.size()];
        for (String k : p.keySet()) {
            NameValuePair nameValue = new NameValuePair(k,String.valueOf(p.get(k)));
            buffer.add(nameValue);
        }
        return buffer.toArray(array);
    }

    /**
     * 执行Http请求
     * 
     * @param request 请求数据
     * @param strParaFileName 文件类型的参数名
     * @param strFilePath 文件路径
     * @return 
     * @throws HttpException, IOException 
     */
    public HttpResponse execute(HttpRequest request, String strParaFileName, String strFilePath) throws HttpException, IOException {
        HttpClient httpclient = new HttpClient(connectionManager);

        // 设置连接超时
        int connectionTimeout = defaultConnectionTimeout;
        if (request.getConnectionTimeout() > 0) {
            connectionTimeout = request.getConnectionTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);

        // 设置回应超时
        int soTimeout = defaultSoTimeout;
        if (request.getTimeout() > 0) {
            soTimeout = request.getTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

        // 设置等待ConnectionManager释放connection的时间
        httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);

        String charset = request.getCharset();
        charset = charset == null ? DEFAULT_CHARSET : charset;
        HttpMethod method = null;

        //get模式且不带上传文件
        if (request.getMethod().equals(HttpRequest.METHOD_GET)) {
            method = new GetMethod(request.getUrl());
            method.getParams().setCredentialCharset(charset);

            // parseNotifyConfig会保证使用GET方法时，request一定使用QueryString
            method.setQueryString(request.getQueryString());
        } else if(strParaFileName.equals("") && strFilePath.equals("")) {
        	//post模式且不带上传文件
            method = new PostMethod(request.getUrl());
            ((PostMethod) method).addParameters(request.getParameters());
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
        }
        else {
        	//post模式且带上传文件
            method = new PostMethod(request.getUrl());
            List<Part> parts = new ArrayList<Part>();
            for (int i = 0; i < request.getParameters().length; i++) {
            	parts.add(new StringPart(request.getParameters()[i].getName(), request.getParameters()[i].getValue(), charset));
            }
            //增加文件参数，strParaFileName是参数名，使用本地文件
            parts.add(new FilePart(strParaFileName, new FilePartSource(new File(strFilePath))));
            
            // 设置请求体
            ((PostMethod) method).setRequestEntity(new MultipartRequestEntity(parts.toArray(new Part[0]), new HttpMethodParams()));
        }

        // 设置Http Header中的User-Agent属性
        method.addRequestHeader("User-Agent", "Mozilla/4.0");
        HttpResponse response = new HttpResponse();

        try {
            httpclient.executeMethod(method);
            if (request.getResultType().equals(HttpResultType.STRING)) {
                response.setStringResult(method.getResponseBodyAsString());
            } else if (request.getResultType().equals(HttpResultType.BYTES)) {
                response.setByteResult(method.getResponseBody());
            }
            response.setResponseHeaders(method.getResponseHeaders());
        } catch (UnknownHostException ex) {

            return null;
        } catch (IOException ex) {

            return null;
        } catch (Exception ex) {

            return null;
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    /**
     * 将NameValuePairs数组转变为字符串
     * 
     * @param nameValues
     * @return
     */
    protected String toString(NameValuePair[] nameValues) {
        if (nameValues == null || nameValues.length == 0) {
            return "null";
        }

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < nameValues.length; i++) {
            NameValuePair nameValue = nameValues[i];

            if (i == 0) {
                buffer.append(nameValue.getName() + "=" + nameValue.getValue());
            } else {
                buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
            }
        }

        return buffer.toString();
    }
    
    public String httpByRequest(HttpServletRequest request, String uri,Map<String,Object> param,String method) {
    	HttpClient httpclient = new HttpClient(connectionManager);
		Cookie cookies[] = request.getCookies();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cookies.length; i++) {
			sb.append(cookies[i].getName()).append("=")
					.append(cookies[i].getValue()).append(";");
		}
		HttpMethod http = null;
        try {
			if(StringUtils.isEmpty(method)){
				method = "post";
			}
			NameValuePair[] parameters = toNameValuePairs(param);
			//get模式
			if ("get".equals(method.toLowerCase())) {
			    http = new GetMethod(uri);
			    http.getParams().setCredentialCharset(DEFAULT_CHARSET);
			    if (parameters!=null) {
					http.setQueryString(parameters);
				}
			}else {
				//post模式
			    http = new PostMethod(uri);
			    http.getParams().setCredentialCharset(DEFAULT_CHARSET);
			    if (parameters!=null) {
					((PostMethod) http).addParameters(toNameValuePairs(param));
				}
			}
			http.addRequestHeader("User-Agent",request.getHeader("User-Agent"));
			http.addRequestHeader("Referer", "http://www.aigou.com/");
			http.addRequestHeader("Accept-Encoding", "gzip, deflate");
			http.addRequestHeader("Connection", "keep-alive");
			http.addRequestHeader("cookie", sb.toString());
			httpclient.executeMethod(http);
			if(http.getRequestHeader("content-Encoding")!=null&&http.getRequestHeader("content-Encoding").getValue().indexOf("gzip")>=0){
				GZIPInputStream gIn=new GZIPInputStream(http.getResponseBodyAsStream());
				byte[] by=new byte[5000];
				StringBuffer  strBuffer=new StringBuffer();
				int len=0;
				while((len=gIn.read(by))!=-1){
					strBuffer.append(new String(by ,0,len,"GBK"));
				}
				return strBuffer.toString();
			}
			return http.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			http.releaseConnection();
		}
		return null;
    }
}