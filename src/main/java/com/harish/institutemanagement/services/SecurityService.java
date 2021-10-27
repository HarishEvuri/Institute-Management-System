package com.harish.institutemanagement.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harish.institutemanagement.config.MyUserDetails;
import com.harish.institutemanagement.models.User;

@Transactional
@Service
public class SecurityService {

	public String findLoggedInUsername() {
		Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (myUserDetails instanceof MyUserDetails) {
			return ((MyUserDetails) myUserDetails).getUser().getUsername();
		}

		return null;
	}

	public User findLoggedInUser() {
		Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (myUserDetails instanceof MyUserDetails) {
			User user = ((MyUserDetails) myUserDetails).getUser();
			return user;
		}
		return null;
	}
}
