package com.lanfranchi.ecommercetest.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.lanfranchi.ecommercetest.dao.BookDAO;
import com.lanfranchi.ecommercetest.models.Book;

@Model
public class AdminListBooksBean {

	@Inject
	private BookDAO bookDAO;
	
	private List<Book> books;

	@PostConstruct
	private void loadObjects() {
		this.books = bookDAO.list();
	}

	public List<Book> getBooks() {
		return books;
	}

}
