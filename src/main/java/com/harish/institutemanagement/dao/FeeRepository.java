package com.harish.institutemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.Fee;

@Repository
public class FeeRepository {

	@Autowired
	private JdbcTemplate template;

	public void updateFee(Fee fee) {
		String sql = "UPDATE Fee SET amount = ? WHERE feeId = ?";
		template.update(sql, fee.getAmount(), fee.getFeeId());
	}

	public void createFee(Fee fee) {
		String sql = "INSERT INTO Fee (feeId, semester, incomeSlab, caste, amount) VALUES (?, ?, ?, ?, ?)";
		template.update(sql, fee.getFeeId(), fee.getSemester(), fee.getIncomeSlab(), fee.getCaste(), fee.getAmount());
	}

	public List<Fee> getAll() {
		String sql = "SELECT * FROM Fee";
		return template.query(sql, new BeanPropertyRowMapper<>(Fee.class));
	}
}
