package com.landcoder.common.util;

import com.landcoder.common.email.MailSenderInfo;
import com.landcoder.common.email.SimpleMailSender;

/**
 * 邮件服务类
 * @author landcoder
 * @company oschina
 */
public class MailSendUtils {

	/**
	 * 发送纯文本格式邮件
	 * @param toMail
	 * @param subject
	 * @param text
	 * @return
	 */
	public static Boolean sendText(String toMail, String subject, String text){
		MailSenderInfo mailInfo = new MailSenderInfo(toMail,subject,text);
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendTextMail(mailInfo);
	}

	/**
	 * 发送html格式邮件
	 * @param toMail
	 * @param subject
	 * @param html
	 * @return
	 */
	public static Boolean sendHtml(String toMail, String subject, String html){
		MailSenderInfo mailInfo = new MailSenderInfo(toMail,subject,html);
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendHtmlMail(mailInfo);
	}
}
