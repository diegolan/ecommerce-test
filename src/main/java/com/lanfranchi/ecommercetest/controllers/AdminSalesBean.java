package com.lanfranchi.ecommercetest.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.lanfranchi.ecommercetest.models.Book;
import com.lanfranchi.ecommercetest.models.Sale;
import com.lanfranchi.ecommercetest.websockets.ConnectedUsers;

@Model
public class AdminSalesBean {

	private Sale sale = new Sale();
	@Inject
	private ConnectedUsers connectedUsers;

	@PostConstruct
	private void configure() {
		this.sale.setBook(new Book());
	}

	public String save() {
		connectedUsers.send(sale.toJson());
		return "/admin/promocoes/form.xhtml?faces-redirect=true";
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

}
