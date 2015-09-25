package org.skcorg.springsecurity.service.impl;

import java.util.Collection;

import org.skcorg.springsecurity.core.authority.CalendarUserAuthorityUtils;
import org.skcorg.springsecurity.domain.CalendarUser;
import org.skcorg.springsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CalendarUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		CalendarUser user = userRepository.findUsersByOpenid(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username/password.");
		}
		return new CalenderUserDetails(user);
	}

	private final class CalenderUserDetails extends CalendarUser implements
			UserDetails {
		private static final long serialVersionUID = 1L;

		public CalenderUserDetails(CalendarUser user) {
			setId(user.getId());
			setEmail(user.getEmail());
			setFirstName(user.getFirstName());
			setLastName(user.getLastName());
			setPassword(user.getPassword());
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return CalendarUserAuthorityUtils.createAuthorities(this);
		}

		@Override
		public String getUsername() {
			return getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	}
}
