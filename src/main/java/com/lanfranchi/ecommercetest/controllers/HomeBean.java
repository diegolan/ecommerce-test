package com.lanfranchi.ecommercetest.controllers;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.lanfranchi.ecommercetest.dao.BookDAO;
import com.lanfranchi.ecommercetest.infra.PopularBD;
import com.lanfranchi.ecommercetest.models.Book;

@Model
public class HomeBean {
	
	@Inject
	private PopularBD populaBD;

	@Inject
	private BookDAO bookDao;

	public List<Book> lastReleases() {
		return bookDao.lastReleases();
	}

	public List<Book> olderBooks() throws Exception {
		if (bookDao.last(20).isEmpty()) {
			populaBD.initBD();
		}
		return bookDao.last(20);
	}

}
