package com.lanfranchi.ecommercetest.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.lanfranchi.ecommercetest.dao.BookDAO;
import com.lanfranchi.ecommercetest.models.Book;

@Model
public class ProductDetailBean {

	@Inject
	private BookDAO bookDAO;
	private Book book = new Book();
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	@PostConstruct
	public void loadBook() {
		if (id != null) {
			this.book = bookDAO.findById(id);
//			System.out.println("getPriceOfBook: "+bookDAO.getPriceOfBook(id)+" -class: "+bookDAO.getPriceOfBook(id).getClass());
		}
	}

	public Book getBook() {
		return book;
	}

}
