package org.adny.work.controller.helper;

import java.util.Date;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.adny.work.property.SystemProperiesConfigure;
import org.jboss.logging.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailHelper {
	
	@Resource
	private JavaMailSender mailSender;
	
	private static final Logger log = Logger.getLogger(EmailHelper.class);
	
	public void send(SimpleMailMessage message) {
		SimpleMailMessage emailMessage = new SimpleMailMessage(message);
		
		String mailUsername = SystemProperiesConfigure.get("mail.username");
		if (log.isDebugEnabled()) {
			log.debug("----------------->Mail Username ------->" + mailUsername);
		}
		emailMessage.setFrom(mailUsername);
		
		this.mailSender.send(emailMessage);
	}
	
	public void send(String to, String subject, String content) {
		int times = 1;
		while (times <= 5) {
			try {
				MimeMessage message = this.mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
				
				messageHelper.setTo(to);
				messageHelper.setSubject(subject);
				
				String mailUsername = SystemProperiesConfigure.get("mail.username");
				if (log.isDebugEnabled()) {
					log.debug("----------------->Mail Username ------->" + mailUsername);
				}
				
				messageHelper.setFrom(mailUsername);
				messageHelper.setSentDate(new Date());
				messageHelper.setText(content, true); // use html
				
				this.mailSender.send(message);
				break;
			} catch (Exception e) {
				log.error("Send email error, try again: " + times, e);
				times++;
			}
		}
	}
	
	public void sendWithAttachment(String to, String subject, String content, Map<String, DataSource> attachments) {
		int times = 1;
		while (times <= 5) {
			try {
				
				MimeMessage message = this.mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
				messageHelper.setTo(to);
				messageHelper.setSubject(subject);
				
				String mailUsername = SystemProperiesConfigure.get("mail.username");
				if (log.isDebugEnabled()) {
					log.debug("----------------->Mail Username ------->" + mailUsername);
				}
				
				messageHelper.setFrom(mailUsername);
				messageHelper.setSentDate(new Date());
				//messageHelper.setText(content, true); // use html
				
				Multipart multipart = new MimeMultipart();
				
				//附件
				BodyPart fileBodyPart = null;
				if (attachments != null && attachments.size() > 0) {
					for (String key : attachments.keySet()) {
						fileBodyPart= new MimeBodyPart();
						fileBodyPart.setFileName(key);
						fileBodyPart.setDataHandler(new DataHandler(attachments.get(key)));
						multipart.addBodyPart(fileBodyPart);
					}
					fileBodyPart = null;
				}
				
				//内容
				BodyPart contentPart = new MimeBodyPart();
				contentPart.setContent(content, "text/html;Charset=utf-8");
				multipart.addBodyPart(contentPart);
				
				message.setContent(multipart);
				
				this.mailSender.send(message);
				break;
			} catch (Exception e) {
				log.error("Send email error, try again: " + times, e);
				e.printStackTrace();
				times++;
			}
		}
	}
	
}
