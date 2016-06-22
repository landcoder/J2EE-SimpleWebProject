package com.landcoder.common.email;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.landcoder.common.util.PropertyUtils;

/**
 * 发送邮件信息
 * @author landcoder
 * @company oschina
 */
public class MailSenderInfo {

	public MailSenderInfo() {
		init();
	}
	
	/**
	 * 邮件发送实体
	 * @param toAddress 带发送的地址
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	public MailSenderInfo(String toAddress, String subject, String content) {
		super();
		init();
		this.toAddress = toAddress;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * 邮件发送实体
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param attachFileNames 邮件附件的文件名
	 */
	public MailSenderInfo(String toAddress, String subject, String content,
			String[] attachFileNames) {
		super();
		init();
		this.toAddress = toAddress;
		this.subject = subject;
		this.content = content;
		this.attachFileNames = attachFileNames;
	}

	private String mailServerHost;// 发送邮件的服务器IP
	private String mailServerPort;// 发送邮件的服务器端口
	private String fromAddress;// 邮件发送者的地址
	private String toAddress;// 邮件接收者的地址
	private String userName;// 登陆邮件发送服务器用户名
	private String password;// 登陆邮件发送服务器密码
	private String validate;// 是否需要身份验证
	private String subject;// 邮件主题
	private String content;// 邮件的文本内容
	private String[] attachFileNames;// 邮件附件的文件名

	/**
	 * 初始化邮件服务器信息
	 */
	private void init() {
		this.mailServerHost = PropertyUtils.getString("javaMail_host");
		this.mailServerPort = PropertyUtils.getString("javaMail_post");
		this.fromAddress = PropertyUtils.getString("javaMail_serverMail");
		this.userName = PropertyUtils.getString("javaMail_username");
		this.password = PropertyUtils.getString("javaMail_password");
		this.validate = PropertyUtils.getString("javaMail_validate");
		if(StringUtils.isEmpty(this.mailServerHost)||StringUtils.isEmpty(this.fromAddress)){
			throw new RuntimeException("Service mailbox is empty !");
		}
	}
	
	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", getMailServerHost());
		p.put("mail.smtp.port", getMailServerPort());
		p.put("mail.smtp.auth", this.validate);
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort==null?"25":mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return !"false".equals(validate);
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}
}
