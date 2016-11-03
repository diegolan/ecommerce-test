package com.lanfranchi.ecommercetest.controllers;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.lanfranchi.ecommercetest.dao.BookDAO;
import com.lanfranchi.ecommercetest.models.Book;
import com.lanfranchi.ecommercetest.models.ShoppingCart;
import com.lanfranchi.ecommercetest.models.ShoppingItem;

@Model
public class ShoppingCartBean {

	@Inject
	private ShoppingCart shoppingCart;
	@Inject
	private BookDAO bookDAO;

	public String add(Integer id) {
		Book book = bookDAO.findById(id);
		ShoppingItem item = new ShoppingItem(book);
		shoppingCart.add(item);
		return "/site/carrinho?faces-redirect=true";
	}

	public String remove(Integer id) {
		Book book = bookDAO.findById(id);
		ShoppingItem item = new ShoppingItem(book);
		shoppingCart.remove(item);
		return "/site/carrinho?faces-redirect=true";
	}

}
