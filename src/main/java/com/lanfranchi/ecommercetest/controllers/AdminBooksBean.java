package com.lanfranchi.ecommercetest.controllers;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import com.lanfranchi.ecommercetest.dao.BookDAO;
import com.lanfranchi.ecommercetest.infra.FileSaver;
import com.lanfranchi.ecommercetest.infra.MessagesHelper;
import com.lanfranchi.ecommercetest.models.Book;

@Model
public class AdminBooksBean {

	@Inject
	private BookDAO bookDAO;
	@Inject
	private MessagesHelper messagesHelper;
	@Inject
	private FileSaver fileSaver;

	private Book product = new Book();
	private Part summary;

	@Transactional
	public String save() {
		
		System.out.println(summary.getName() + ";" + extractFilename(summary.getHeader("content-disposition")));
		
		String summaryPath = fileSaver.write("summaries", summary);
		product.setSummaryPath(summaryPath);
		bookDAO.save(product);

		messagesHelper.addFlash(new FacesMessage("Livro gravado com sucesso"));

		return "/livros/lista?faces-redirect=true";
	}

	public Book getProduct() {
		return product;
	}

	public Part getSummary() {
		return summary;
	}

	public void setSummary(Part summary) {
		this.summary = summary;
	}
	
	private String extractFilename(String contentDisposition) {
		if (contentDisposition == null) {
			return null;
		}
		String fileNameKey = "filename=";
		int startIndex = contentDisposition.indexOf(fileNameKey);
		if (startIndex == -1) {
			return null;
		}
		String filename = contentDisposition.substring(startIndex + fileNameKey.length());
		if (filename.startsWith("\"")) {
			int endIndex = filename.indexOf("\"", 1);
			if (endIndex != -1) {
				return filename.substring(1, endIndex);
			}
		} else {
			int endIndex = filename.indexOf(";");
			if (endIndex != -1) {
				return filename.substring(0, endIndex);
			}
		}
		return filename;
	}

}
