package org.skcorg.springsecurity.service.impl;

import java.util.List;

import org.skcorg.springsecurity.domain.CalendarUser;
import org.skcorg.springsecurity.domain.Event;
import org.skcorg.springsecurity.repositories.EventRepository;
import org.skcorg.springsecurity.repositories.UserRepository;
import org.skcorg.springsecurity.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A default implementation of {@link CalendarService} that delegates to
 * {@link EventDao} and {@link CalendarUserDao}.
 * 
 * @author Sumit Chandna
 * 
 */
@Service
@Transactional
public class DefaultCalendarService implements CalendarService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EventRepository eventRepository;

	public void setUserRepository(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setEventRepository(final EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public EventRepository getEventRepository() {
		return eventRepository;
	}

	@Override
	public Event getEvent(final int eventId) {
		return eventRepository.getEvent(eventId);
	}

	@Override
	public Event createEvent(final Event event) {
		Event pevent = eventRepository.save(event);
		return null != pevent.getId() ? pevent : null;
	}

	@Override
	public List<Event> findForUser(final int userId) {
		return eventRepository.findEventForUser(userId);
	}

	@Override
	public List<Event> getEvents() {
		return eventRepository.findAll();
	}

	@Override
	public CalendarUser getUser(final int id) {
		return userRepository.getUser(id);
	}

	@Override
	public CalendarUser findUserByEmail(final String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public List<CalendarUser> findUsersByEmail(final String partialEmail) {
		return userRepository.findUsersByEmail(partialEmail);
	}

	@Override
	public CalendarUser createUser(final CalendarUser user) {
		CalendarUser calendarUser = userRepository.save(user);
		if (null != calendarUser.getId()) {
			return calendarUser;
		}
		return null;
	}
}