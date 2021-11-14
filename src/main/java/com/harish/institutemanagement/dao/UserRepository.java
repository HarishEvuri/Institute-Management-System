package com.harish.institutemanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.User;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void createUser(User user) {
		user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPasswordHash()));
		String sql = "INSERT INTO User (username, passwordHash, firstName, lastName, dateOfBirth, emailAddress, gender, address, lastLoginTime, token, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		template.update(sql, user.getUsername(), user.getPasswordHash(), user.getFirstName(), user.getLastName(),
				user.getDateOfBirth(), user.getEmailAddress(), user.getGender(), user.getAddress(),
				user.getLastLoginTime(), user.getToken(), user.getRole());

	}

	public User getUser(String username) {

		try {
			String sql = "SELECT * FROM User WHERE username = ?";
			return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), new Object[] { username });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updateUser(User user) {

		String sql = "UPDATE User SET firstName = ?, lastName = ?, emailAddress = ?, dateOfBirth = ?, gender = ?, address = ? WHERE username = ?";

		template.update(sql, user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getDateOfBirth(),
				user.getGender(), user.getAddress(), user.getUsername());

	}

	public void updateToken(User user) {

		String sql = "UPDATE User SET token = ? WHERE username = ?";
		template.update(sql, user.getToken(), user.getUsername());
	}

	public User getUserByToken(String token) {
		try {
			String sql = "SELECT * FROM User WHERE token = ?";
			return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), new Object[] { token });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void changePassword(String username, String password) {
		String passwordHash = bCryptPasswordEncoder.encode(password);
		String sql = "UPDATE User SET passwordHash = ? WHERE username = ?";
		template.update(sql, passwordHash, username);
	}

	public void deleteUser(String username) {
		String sql = "DELETE FROM User WHERE username = ?";
		template.update(sql, username);
	}
}
