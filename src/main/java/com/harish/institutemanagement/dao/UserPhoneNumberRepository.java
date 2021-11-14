package com.harish.institutemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.UserPhoneNumber;

@Repository
public class UserPhoneNumberRepository {

	@Autowired
	private JdbcTemplate template;

	public void createPhoneNumber(UserPhoneNumber userPhoneNumber) {
		String sql = "INSERT INTO UserPhoneNumber (username, phoneNumber) VALUES (?, ?)";
		template.update(sql, userPhoneNumber.getUsername(), userPhoneNumber.getPhoneNumber());
	}

	public void deletePhoneNumber(String username, String userPhoneNumber) {
		String sql = "DELETE FROM UserPhoneNumber WHERE username = ? AND phoneNumber = ?";
		template.update(sql, username, userPhoneNumber);
	}

	public UserPhoneNumber getPhoneNumber(String username, String userPhoneNumber) {
		try {
			String sql = "SELECT * FROM UserPhoneNumber WHERE username = ? AND phoneNumber = ?";
			return template.queryForObject(sql, new BeanPropertyRowMapper<>(UserPhoneNumber.class),
					new Object[] { username, userPhoneNumber });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<UserPhoneNumber> getPhoneNumbersByUsername(String username) {
		String sql = "SELECT * FROM UserPhoneNumber WHERE username = ?";
		return template.query(sql, new BeanPropertyRowMapper<>(UserPhoneNumber.class), new Object[] { username });
	}
}
