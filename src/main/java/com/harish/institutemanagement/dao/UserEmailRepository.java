package com.harish.institutemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.UserEmail;

@Repository
public class UserEmailRepository {

	@Autowired
	private JdbcTemplate template;

	public void createEmail(UserEmail userEmail) {
		String sql = "INSERT INTO UserEmail (username, email) VALUES (?, ?)";
		template.update(sql, userEmail.getUsername(), userEmail.getEmail());
	}

	public void deleteEmail(UserEmail userEmail) {
		String sql = "DELETE FROM UserEmail WHERE username = ? AND email = ?";
		template.update(sql, userEmail.getUsername(), userEmail.getEmail());
	}

	public List<UserEmail> getEmailsByUsername(String username) {
		String sql = "SELECT * FROM UserEmail WHERE username = ?";
		return template.query(sql, new BeanPropertyRowMapper<>(UserEmail.class));
	}
}
