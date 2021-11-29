package com.harish.institutemanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harish.institutemanagement.dao.UserRepository;
import com.harish.institutemanagement.models.User;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Lazy
	@Autowired
	private UserRepository userRepository;

	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.getUser(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username");
		}

		// Need to update timeStamp
		return new MyUserDetails(user);
	}

}
