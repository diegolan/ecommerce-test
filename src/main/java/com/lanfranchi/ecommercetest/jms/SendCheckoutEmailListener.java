package com.lanfranchi.ecommercetest.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lanfranchi.ecommercetest.dao.CheckoutDAO;
import com.lanfranchi.ecommercetest.infra.MailSender;
import com.lanfranchi.ecommercetest.models.Checkout;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationLookup", 
				propertyValue = "java:/jms/topics/checkoutsTopic")
		})
public class SendCheckoutEmailListener implements MessageListener {
	
	private Logger logger = LoggerFactory.getLogger(SendCheckoutEmailListener.class);
	@Inject
	private MailSender mailSender;
	@Inject
	private CheckoutDAO checkoutDao;

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		try {
			Checkout checkout = checkoutDao.findByUuid(text.getText());
			String emailBody = "<html><body>Compra realizada com sucesso. O codigo de acompanhamento e "
					+ checkout.getUuid() + "</body></html>";
			
			mailSender.send("diego.lanfranchi@gmail.com", checkout.getBuyer()
					.getEmail(), "Sua compra foi registrada com sucesso",
					emailBody);
//			System.out.println("E-MAIL ENVIADO "+checkout);
		} catch (Exception e) {
			logger.error("Problema no envio do email",e);
		}
	}

}
