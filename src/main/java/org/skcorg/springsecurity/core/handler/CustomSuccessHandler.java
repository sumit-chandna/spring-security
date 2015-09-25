package org.skcorg.springsecurity.core.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skcorg.springsecurity.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	private String companyInfoURL;
	@Autowired
	private UserContext userContext;

	public UserContext getUserContext() {
		return userContext;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}

	public String getCompanyInfoURL() {
		return companyInfoURL;
	}

	public void setCompanyInfoURL(String companyInfoURL) {
		this.companyInfoURL = companyInfoURL;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		if (this.companyInfoURL != null) {
			getRedirectStrategy().sendRedirect(request, response,
					companyInfoURL);
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}
