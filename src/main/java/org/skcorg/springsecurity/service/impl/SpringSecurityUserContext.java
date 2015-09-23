package org.skcorg.springsecurity.service.impl;

import java.util.Collection;

import org.skcorg.springsecurity.core.authority.CalendarUserAuthorityUtils;
import org.skcorg.springsecurity.domain.CalendarUser;
import org.skcorg.springsecurity.service.UserContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityUserContext implements UserContext {

	@Override
	public CalendarUser getCurrentUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication == null) {
			return null;
		}
		return (CalendarUser) authentication.getPrincipal();
	}

	@Override
	public void setCurrentUser(CalendarUser user) {
		Collection<? extends GrantedAuthority> authorities = CalendarUserAuthorityUtils
				.createAuthorities(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				user, user.getPassword(), authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
