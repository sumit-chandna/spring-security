package org.skcorg.springsecurity.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skcorg.springsecurity.core.token.DomainUserNamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class DomainUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equalsIgnoreCase("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		String domain = request.getParameter("domain");
		DomainUserNamePasswordAuthenticationToken authRequest = new DomainUserNamePasswordAuthenticationToken(
				username, password, domain);
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

}
