package com.lanfranchi.ecommercetest.resources;

import java.math.BigDecimal;
import java.net.URI;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.lanfranchi.ecommercetest.dao.CheckoutDAO;
import com.lanfranchi.ecommercetest.models.Checkout;
import com.lanfranchi.ecommercetest.models.PaymentGateway;

@Path("payment")
public class PaymentResource {

	@Context
	private ServletContext ctx;

	@Inject
	private CheckoutDAO checkoutDao;

	@Inject
	private PaymentGateway paymentGateway;

	@Resource(name = "java:comp/DefaultManagedExecutorService")
	private ManagedExecutorService managedExecutorService;
	
	@Inject
	private JMSContext jmsContext;
	
	@Resource(lookup = "java:/jms/topics/checkoutsTopic")
	private Destination checkoutsTopic;

	@POST
	public void pay(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		String contextPath = ctx.getContextPath();
		Checkout checkout = checkoutDao.findByUuid(uuid);
		JMSProducer producer = jmsContext.createProducer();
		managedExecutorService.submit(() -> {

			BigDecimal total = checkout.getValue();

			try {
				paymentGateway.pay(total);
				
				producer.send(checkoutsTopic, checkout.getUuid());

				URI redirectURI = UriBuilder.fromUri(contextPath + "/site/index.xhtml")
						.queryParam("msg", "Compra realizada com sucesso").build();
				Response response = Response.seeOther(redirectURI).build();
				ar.resume(response);

			} catch (Exception e) {
				ar.resume(e);
			}

		});

	}

}
