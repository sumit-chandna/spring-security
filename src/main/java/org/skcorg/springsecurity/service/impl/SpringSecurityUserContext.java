package org.skcorg.springsecurity.service.impl;

import org.skcorg.springsecurity.domain.CalendarUser;
import org.skcorg.springsecurity.service.CalendarService;
import org.skcorg.springsecurity.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityUserContext implements UserContext {
	@Autowired
	private CalendarService calendarService;

	public CalendarService getCalendarService() {
		return calendarService;
	}

	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	@Override
	public CalendarUser getCurrentUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication == null) {
			return null;
		}
		return calendarService.findUserByEmail(authentication.getName());
	}

	@Override
	public void setCurrentUser(CalendarUser user) {
		throw new UnsupportedOperationException();
	}

}
