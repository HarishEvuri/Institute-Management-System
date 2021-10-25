package com.harish.institutemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.Fee;
import com.harish.institutemanagement.models.FeePayment;

@Repository
public class FeePaymentRepository {

	@Autowired
	private JdbcTemplate template;

	public void createFeePayment(FeePayment feePayment) {
		String sql = "INSERT INTO FeePayment (transactionId, rollNumber, feeId, transactionTime, modeOfPayment) VALUES (?, ?, ?, ?, ?)";
		template.update(sql, feePayment.getTransactionId(), feePayment.getRollNumber(), feePayment.getFee().getFeeId(),
				feePayment.getTransactionTime(), feePayment.getModeOfPayment());
	}

	public void deleteFeePayment(String transactionId) {
		String sql = "DELETE FROM FeePayment WHERE transactionId = ?";
		template.update(sql, transactionId);
	}

	public List<FeePayment> getFeePaymentsByRollNumber(String rollNumber) {
		String sql = "SELECT * FROM FeePayment NATURAL JOIN Fee WHERE rollNumber = ?";
		return template.query(sql, new RowMapper<FeePayment>() {

			public FeePayment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Fee fee = (new BeanPropertyRowMapper<>(Fee.class)).mapRow(rs, rowNum);

				FeePayment feePayment = (new BeanPropertyRowMapper<>(FeePayment.class)).mapRow(rs, rowNum);

				feePayment.setFee(fee);
				return feePayment;
			}

		}, new Object[] { rollNumber });
	}

}
