package com.lanfranchi.ecommercetest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import com.lanfranchi.ecommercetest.models.Book;
import com.lanfranchi.ecommercetest.services.BookService;

@Path("books")
public class BooksResource {

	@Inject
	private BookService bookService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("json")
	public List<Book> lastBooksJson() {
		return bookService.lastReleases();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("xml")
	@Wrapped(element="books")
	public List<Book> lastBooks() {
		return bookService.lastReleases();
	}

}
