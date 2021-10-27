package com.harish.institutemanagement.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.harish.institutemanagement.models.User;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> grantList = new ArrayList<>();
		if (user == null) {
			return grantList;
		}
		GrantedAuthority authority = new SimpleGrantedAuthority(this.user.getRole());
		grantList.add(authority);
		return grantList;
	}

	@Override
	public String getPassword() {
		return this.user.getPasswordHash();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
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

	public MyUserDetails(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
