package org.skcorg.springsecurity.repositories;

import java.util.List;

import org.skcorg.springsecurity.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

	public Event getEvent(@Param("id") int eventId);

	public List<Event> findEventForUser(@Param("user") int user);
}
