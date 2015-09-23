package org.skcorg.springsecurity.core.token;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class DomainUserNamePasswordAuthenticationToken extends
		UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String domain;

	public String getDomain() {
		return domain;
	}

	public DomainUserNamePasswordAuthenticationToken(Object principal,
			Object credentials,
			Collection<? extends GrantedAuthority> authorities, String domain) {
		super(principal, credentials, authorities);
		this.domain = domain;
	}

	public DomainUserNamePasswordAuthenticationToken(Object principal,
			Object credentials, String domain) {
		super(principal, credentials);
		this.domain = domain;
	}

}
