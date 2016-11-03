package com.lanfranchi.ecommercetest.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lanfranchi.ecommercetest.models.SystemUser;

public class SystemUserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(SystemUser systemUser) {
		entityManager.persist(systemUser);
	}

}
