package com.lanfranchi.ecommercetest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.lanfranchi.ecommercetest.dao.AuthorDAO;
import com.lanfranchi.ecommercetest.models.Author;

@Model
public class AuthorsList {
	
	@Inject
	private AuthorDAO authorDAO;
	private List<Author> authors = new ArrayList<Author>();
	
	@PostConstruct
	private void loadObjects(){
		this.authors = authorDAO.list();
	}
	
	public List<Author> get() {
		return authors;
	}

}
