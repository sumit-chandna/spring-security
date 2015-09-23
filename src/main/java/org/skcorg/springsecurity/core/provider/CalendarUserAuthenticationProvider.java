package org.skcorg.springsecurity.core.provider;

import java.util.Collection;

import org.skcorg.springsecurity.core.authority.CalendarUserAuthorityUtils;
import org.skcorg.springsecurity.core.token.DomainUserNamePasswordAuthenticationToken;
import org.skcorg.springsecurity.domain.CalendarUser;
import org.skcorg.springsecurity.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CalendarUserAuthenticationProvider implements
		AuthenticationProvider {
	@Autowired
	private CalendarService calendarService;

	public CalendarService getCalendarService() {
		return calendarService;
	}

	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		DomainUserNamePasswordAuthenticationToken token = (DomainUserNamePasswordAuthenticationToken) authentication;
		String userName = token.getName();
		String domain = token.getDomain();
		String email = userName + "@" + domain;
		CalendarUser user = null;
		if (email != null) {
			user = calendarService.findUserByEmail(email);
		}
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username/password");
		}
		String password = user.getPassword();
		if (!password.equals(token.getCredentials())) {
			throw new BadCredentialsException("Invalid username/password");
		}
		Collection<? extends GrantedAuthority> authorities = CalendarUserAuthorityUtils
				.createAuthorities(user);
		return new DomainUserNamePasswordAuthenticationToken(user, password,
				authorities, domain);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return DomainUserNamePasswordAuthenticationToken.class
				.equals(authentication);
	}

}
