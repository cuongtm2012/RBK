package com.vn.rbk.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GmailMsg {
	public static void process(String date, String number) {
		final String fromEmail = "cuong8888q@gmail.com"; 	// requires valid gmail
															// id
		final String password = "cuongtm2015"; 				// correct password for gmail id
		final String toEmail = "cuongtm2012@gmail.com"; 	// can be any email id

		log.debug("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		log.debug("Session created");
		sendEmail(session, toEmail, "Analysis System. Date : " + date, "List output number : " + number);
	}

	public static void sendEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@example.com", "MC Company"));

			msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			log.debug("Message is ready");
			Transport.send(msg);

			log.debug("EMail Sent Successfully!!");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
