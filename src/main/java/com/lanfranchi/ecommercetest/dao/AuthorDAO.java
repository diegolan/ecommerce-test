package com.lanfranchi.ecommercetest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lanfranchi.ecommercetest.models.Author;

public class AuthorDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public List<Author> list() {
		return manager.createQuery("select a from Author a", Author.class).getResultList();
	}
	
	public void save(Author author) {
		manager.persist(author);
	}

}
