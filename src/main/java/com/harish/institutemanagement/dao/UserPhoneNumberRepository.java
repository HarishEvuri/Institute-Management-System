package com.harish.institutemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void deletePhoneNumber(UserPhoneNumber userPhoneNumber) {
		String sql = "DELETE FROM UserPhoneNumber WHERE username = ? AND phoneNumber = ?";
		template.update(sql, userPhoneNumber.getUsername(), userPhoneNumber.getPhoneNumber());
	}

	public List<UserPhoneNumber> getPhoneNumbersByUsername(String username) {
		String sql = "SELECT * FROM UserPhoneNumber WHERE username = ?";
		return template.query(sql, new BeanPropertyRowMapper<>(UserPhoneNumber.class));
	}
}
