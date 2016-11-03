package com.lanfranchi.ecommercetest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.QueryHints;

import com.lanfranchi.ecommercetest.models.Book;

public class BookDAO {

	@PersistenceContext
	private EntityManager manager;

	public void save(Book product) {
		manager.persist(product);
	}
	
	public List<Book> list() {
		return manager.createQuery("select distinct(b) from Book b join fetch b.authors", Book.class).getResultList();
	}

	public List<Book> lastReleases() {
		TypedQuery<Book> query = manager.createQuery("select b from Book b where b.releaseDate <= now() order by b.id desc", Book.class).setMaxResults(3);
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		query.setHint(QueryHints.HINT_CACHE_REGION, "home");
		return query.getResultList();
	}
	
	public List<Book> last(int number) {
		TypedQuery<Book> query = manager.createQuery("select b from Book b join fetch b.authors", Book.class).setMaxResults(number);
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		query.setHint(QueryHints.HINT_CACHE_REGION, "home");
		return query.getResultList();
	}

	public Book findById(Integer id) {
		return manager.find(Book.class, id);
	}
	
//	public BigDecimal getPriceOfBook(Integer id) {
//		return (BigDecimal)manager.createQuery("select price from Book b where b.id=:id").setParameter("id", id).getSingleResult();
//	}

}
