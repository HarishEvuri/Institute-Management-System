package com.harish.institutemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.FeePayment;
import com.harish.institutemanagement.models.Student;
import com.harish.institutemanagement.models.User;

@Repository
public class FeePaymentRepository {

	@Autowired
	private JdbcTemplate template;

	public void createFeePayment(FeePayment feePayment) {
		String sql = "INSERT INTO FeePayment (transactionId, rollNumber, transactionDate, transactionTime, semester, year, amount, modeOfPayment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, feePayment.getTransactionId(), feePayment.getStudent().getRollNumber(),
				feePayment.getTransactionDate(), feePayment.getTransactionTime(), feePayment.getSemester(),
				feePayment.getYear(), feePayment.getAmount(), feePayment.getModeOfPayment());
	}

	public void updateFeePayment(FeePayment feePayment) {
		String sql = "UPDATE FeePayment SET rollNumber = ?, transactionDate = ?, transactionTime = ?, semester = ?, year = ?, amount = ?, modeOfPayment = ? WHERE transactionId = ?";
		template.update(sql, feePayment.getStudent().getRollNumber(), feePayment.getTransactionDate(),
				feePayment.getTransactionTime(), feePayment.getSemester(), feePayment.getYear(), feePayment.getAmount(),
				feePayment.getModeOfPayment(), feePayment.getTransactionId());
	}

	public void deleteFeePayment(String transactionId) {
		String sql = "DELETE FROM FeePayment WHERE transactionId = ?";
		template.update(sql, transactionId);
	}

	public FeePayment getFeePayment(String transactionId) {
		try {
			String sql = "SELECT * FROM FeePayment WHERE transactionId = ?";
			return template.queryForObject(sql, new RowMapper<FeePayment>() {

				public FeePayment mapRow(ResultSet rs, int rowNum) throws SQLException {
					Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
					FeePayment feePayment = (new BeanPropertyRowMapper<>(FeePayment.class)).mapRow(rs, rowNum);
					feePayment.setStudent(student);
					return feePayment;
				}

			}, new Object[] { transactionId });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<FeePayment> getAll() {
		String sql = "SELECT * FROM FeePayment NATURAL JOIN Student NATURAL JOIN User";
		return template.query(sql, new RowMapper<FeePayment>() {

			public FeePayment mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
				Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
				FeePayment feePayment = (new BeanPropertyRowMapper<>(FeePayment.class)).mapRow(rs, rowNum);

				student.setUser(user);
				feePayment.setStudent(student);

				return feePayment;
			}

		});
	}

	public List<FeePayment> getFeePaymentsByRollNumber(String rollNumber) {
		String sql = "SELECT * FROM FeePayment WHERE rollNumber = ?";
		return template.query(sql, new BeanPropertyRowMapper<>(FeePayment.class), new Object[] { rollNumber });
	}

}
