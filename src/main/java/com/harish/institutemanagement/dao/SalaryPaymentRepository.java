package com.harish.institutemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.Salary;
import com.harish.institutemanagement.models.SalaryPayment;

@Repository
public class SalaryPaymentRepository {

	@Autowired
	private JdbcTemplate template;

	public void createSalaryPayment(SalaryPayment salaryPayment) {
		String sql = "INSERT INTO SalaryPayment (transactionId, transactionTime, employeeId, salaryId) VALUES (?, ?, ?, ?)";
		template.update(sql, salaryPayment.getTransactionId(), salaryPayment.getTransactionTime(),
				salaryPayment.getEmployeeId(), salaryPayment.getSalary().getSalaryId());
	}

	public void deleteSalaryPayment(String transactionId) {
		String sql = "DELETE FROM SalaryPayment WHERE transactionId = ?";
		template.update(sql, transactionId);
	}

	public List<SalaryPayment> getSalaryPaymentsByEmployeeId(String employeeId) {
		String sql = "SELECT * FROM SalaryPayment NATURAL JOIN Salary WHERE employeeId = ?";

		return template.query(sql, new RowMapper<SalaryPayment>() {

			public SalaryPayment mapRow(ResultSet rs, int rowNum) throws SQLException {

				Salary salary = (new BeanPropertyRowMapper<>(Salary.class)).mapRow(rs, rowNum);
				SalaryPayment salaryPayment = (new BeanPropertyRowMapper<>(SalaryPayment.class)).mapRow(rs, rowNum);

				salaryPayment.setSalary(salary);
				return salaryPayment;
			}

		}, new Object[] { employeeId });
	}
}
