package com.lanfranchi.ecommercetest.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lanfranchi.ecommercetest.models.SystemUser;

public class SecurityDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public SystemUser loadUserByUsername(String name) {
		return manager.createQuery("select u from SystemUser u where u.email = :email", SystemUser.class).setParameter("email", name).getSingleResult();
	}

}
