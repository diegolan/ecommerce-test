package com.lanfranchi.ecommercetest.jms;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import com.lanfranchi.ecommercetest.models.Checkout;
import com.lanfranchi.ecommercetest.models.InvoiceData;

public class InvoiceGenerator {

	public void invoiceFor(Checkout checkout) {
		Client client = ClientBuilder.newClient();
		InvoiceData invoiceData = new InvoiceData(checkout);
		String uriToGenerateInvoice = "http://book-payment.herokuapp.com/invoice";
		Entity<InvoiceData> json = Entity.json(invoiceData);
		client.target(uriToGenerateInvoice).request().post(json, String.class);		
	}

	
}
