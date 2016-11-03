package com.lanfranchi.ecommercetest.infra;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static void main(String[] args) throws Exception {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(props);
		session.setDebug(true);

		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("diego.lanfranchi@gmail.com"));
		mimeMessage.setFrom(new InternetAddress("diego.lanfranchi@gmail.com"));
		mimeMessage.setSubject("Sua compra foi registrada");
		mimeMessage.setContent("testando msg sender", "text/html");
		
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", "diego.lanfranchi@gmail.com", "");
		Transport.send(mimeMessage);
		transport.close();

	}

}
