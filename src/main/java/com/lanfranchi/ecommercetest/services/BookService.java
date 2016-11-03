package com.lanfranchi.ecommercetest.services;

import java.util.List;

import javax.inject.Inject;

import com.lanfranchi.ecommercetest.dao.BookDAO;
import com.lanfranchi.ecommercetest.models.Book;

public class BookService {
	
	@Inject
	private BookDAO bookDAO;
	
	public List<Book> lastReleases() {
		return bookDAO.lastReleases();
	}

}
