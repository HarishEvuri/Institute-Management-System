package com.harish.institutemanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.User;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate template;

	public void createUser(User user) {
		String sql = "INSERT INTO User (username, passwordHash, firstName, lastName, dateOfBirth, gender, address, lastLoginTime, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		template.update(sql, user.getUsername(), user.getPasswordHash(), user.getFirstName(), user.getLastName(),
				user.getDateOfBirth(), user.getGender(), user.getAddress(), user.getLastLoginTime(), user.getRole());
	}

	public User getUser(String username) {
		String sql = "SELECT * FROM User WHERE username = ?";

		return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), new Object[] { username });
	}

	public void updateUser(User user) {
		String sql = "UPDATE User SET passwordHash = ?, firstName = ?, lastName = ?, dateOfBirth = ?, gender = ?, address = ? WHERE username = ?";

		template.update(sql, user.getPasswordHash(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(),
				user.getGender(), user.getAddress(), user.getUsername());

	}
}
