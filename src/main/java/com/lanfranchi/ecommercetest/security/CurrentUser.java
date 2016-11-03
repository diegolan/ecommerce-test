package com.lanfranchi.ecommercetest.security;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.lanfranchi.ecommercetest.dao.SecurityDAO;
import com.lanfranchi.ecommercetest.models.SystemUser;

@Model
public class CurrentUser {

	@Inject
	private HttpServletRequest request;
	@Inject
	private SecurityDAO securityDAO;
	private SystemUser systemUser;

	public boolean hasRole(String name) {
		return request.isUserInRole(name);
	}

	public String getLogin() {
		return request.getUserPrincipal().getName();
	}
	
	public SystemUser get() {
		return this.systemUser;
	}

	@PostConstruct
	private void loadSystemUser() {
		if (request.getUserPrincipal() != null) {
			this.systemUser = securityDAO.loadUserByUsername(request.getUserPrincipal().getName());
		}
	}
}
