package com.lanfranchi.ecommercetest.infra;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.lanfranchi.ecommercetest.dao.AuthorDAO;
import com.lanfranchi.ecommercetest.dao.BookDAO;
import com.lanfranchi.ecommercetest.models.Author;
import com.lanfranchi.ecommercetest.models.Book;
import com.lanfranchi.ecommercetest.models.SystemRole;
import com.lanfranchi.ecommercetest.models.SystemUser;
import com.lanfranchi.ecommercetest.security.PassGenerator;

@ApplicationScoped
public class PopularBD {
	
	@Inject
	private BookDAO bookDAO;
	
	@Inject
	private AuthorDAO authorDAO;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public void initBD() throws Exception {
		
		Author a1 = new Author();
		a1.setName("Martin Fowler");
		
		Author a2 = new Author();
		a2.setName("Kent Beck");
		
		Author a3 = new Author();
		a3.setName("Frank Appel");
		
		authorDAO.save(a1);
		authorDAO.save(a2);
		authorDAO.save(a3);
		
		Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, 10);
		
		String paths = "http://www.walcordeiro.com.br/v1/wp-content/uploads/capa-livro-finan%C3%A7as1.jpg";
		
		Book b1 = new Book();
		b1.getAuthors().add(a1);
		b1.setTitle("Livro 1");
		b1.setDescription("Minha descrição 1 do livro 1");
		b1.setNumberOfPages(330);
		b1.setPrice(new BigDecimal(129.90d));
		b1.setReleaseDate(now);
		b1.setSummaryPath(paths);
		b1.setCoverPath(paths);
		
		Book b2 = new Book();
		b2.getAuthors().add(a2);
		b2.setTitle("Livro 2");
		b2.setDescription("Minha descrição 2 do livro 2");
		b2.setNumberOfPages(250);
		b2.setPrice(new BigDecimal(123.99d));
		b2.setReleaseDate(now);
		b2.setSummaryPath(paths);
		b2.setCoverPath(paths);
		
		Book b3 = new Book();
		b3.getAuthors().add(a1);
		b3.getAuthors().add(a2);
		b3.getAuthors().add(a3);
		b3.setTitle("Livro 3");
		b3.setDescription("Minha descrição 3 do livro 3");
		b3.setNumberOfPages(300);
		b3.setPrice(new BigDecimal(199.99d));
		b3.setReleaseDate(now);
		b3.setSummaryPath(paths);
		b3.setCoverPath(paths);
		
		bookDAO.save(b1);
		bookDAO.save(b2);
		bookDAO.save(b3);
		
		SystemRole admRole = new SystemRole("ADMIN");
		
		entityManager.persist(admRole);
		
		SystemUser adminUser = new SystemUser();
		adminUser.setEmail("admin@admin.com");
		adminUser.setPassword(PassGenerator.generate("1234"));
		adminUser.getRoles().add(admRole);
		
		entityManager.persist(adminUser);
		
		System.out.println("adminUser.getPassword(): "+adminUser.getPassword());
		
	}

}
