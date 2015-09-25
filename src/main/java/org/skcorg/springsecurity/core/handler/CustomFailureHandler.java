package org.skcorg.springsecurity.core.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skcorg.springsecurity.domain.CalendarUser;
import org.skcorg.springsecurity.model.SignupForm;
import org.skcorg.springsecurity.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {
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

	@SuppressWarnings("deprecation")
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		if (exception instanceof UsernameNotFoundException
				&& exception.getAuthentication() instanceof OpenIDAuthenticationToken) {
			OpenIDAuthenticationToken token = (OpenIDAuthenticationToken) exception
					.getAuthentication();
			if (OpenIDAuthenticationStatus.SUCCESS.equals(token.getStatus())) {
				// getting attributes passed by google/openID provider
				final List<OpenIDAttribute> attrList = token.getAttributes();
				String username = (String) token.getPrincipal();
				DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
				SignupForm signupForm = new SignupForm();
				signupForm.setEmail("test@mail.com");
				signupForm.setOpenId(username);
				request.getSession(true).setAttribute("signupForm", signupForm);
				redirectStrategy
						.sendRedirect(request, response, companyInfoURL);
			} else {
				super.onAuthenticationFailure(request, response, exception);
			}
		}
	}
}