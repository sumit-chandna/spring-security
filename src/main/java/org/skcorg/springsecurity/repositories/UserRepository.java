package org.skcorg.springsecurity.repositories;

import java.io.Serializable;
import java.util.List;

import org.skcorg.springsecurity.domain.CalendarUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
		JpaRepository<CalendarUser, Serializable> {

	public CalendarUser getUser(@Param("id") int id);

	public CalendarUser findUserByEmail(@Param("email") String email);

	public List<CalendarUser> findUsersByEmail(@Param("email") String email);
}
