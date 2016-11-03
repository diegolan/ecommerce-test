package com.lanfranchi.ecommercetest.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lanfranchi.ecommercetest.models.Checkout;

public class CheckoutDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Checkout checkout) {
		entityManager.persist(checkout);
	}

	public Checkout findByUuid(String uuid) {
		return entityManager.createQuery("select c from Checkout c join fetch c.buyer where c.uuid=:uuid", Checkout.class).setParameter("uuid", uuid).getSingleResult();
	}

}
