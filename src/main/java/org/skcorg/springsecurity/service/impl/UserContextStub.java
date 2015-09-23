package org.skcorg.springsecurity.service.impl;

import org.skcorg.springsecurity.domain.CalendarUser;
import org.skcorg.springsecurity.service.CalendarService;
import org.skcorg.springsecurity.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Returns the same user for every call to {@link #getCurrentUser()}. This is
 * used prior to adding security, so that the rest of the application can be
 * used.
 * 
 * @author Sumit Chandna
 */
// @Component
public class UserContextStub implements UserContext {
	@Autowired
	private CalendarService calendarService;

	public CalendarService getCalendarService() {
		return calendarService;
	}

	public void setCalendarService(final CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	/**
	 * The {@link CalendarUser#getId()} for the user that is representing the
	 * currently logged in user. This can be modified using
	 * {@link #setCurrentUser(CalendarUser)}
	 */
	private int currentUserId = 0;

	@Override
	public CalendarUser getCurrentUser() {
		return calendarService.getUser(currentUserId);
	}

	@Override
	public void setCurrentUser(final CalendarUser user) {
		if (user == null) {
			throw new IllegalArgumentException("user cannot be null");
		}
		final Integer currentId = user.getId();
		if (currentId == null) {
			throw new IllegalArgumentException("user.getId() cannot be null");
		}
		this.currentUserId = currentId;
	}
}