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

import com.harish.institutemanagement.models.Employee;
import com.harish.institutemanagement.models.SalaryPayment;
import com.harish.institutemanagement.models.User;

@Repository
public class SalaryPaymentRepository {

	@Autowired
	private JdbcTemplate template;

	public void createSalaryPayment(SalaryPayment salaryPayment) {
		String sql = "INSERT INTO SalaryPayment (transactionId, employeeId, transactionDate, transactionTime, month, year, amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, salaryPayment.getTransactionId(), salaryPayment.getEmployee().getEmployeeId(),
				salaryPayment.getTransactionDate(), salaryPayment.getTransactionTime(), salaryPayment.getMonth(),
				salaryPayment.getYear(), salaryPayment.getAmount());
	}

	public void updateSalaryPayment(SalaryPayment salaryPayment) {
		String sql = "UPDATE SalaryPayment SET employeeId = ?, transactionDate = ?, transactionTime = ?, month = ?, year = ?, amount = ? WHERE transactionId = ?";
		template.update(sql, salaryPayment.getEmployee().getEmployeeId(), salaryPayment.getTransactionDate(),
				salaryPayment.getTransactionTime(), salaryPayment.getMonth(), salaryPayment.getYear(),
				salaryPayment.getAmount(), salaryPayment.getTransactionId());
	}

	public void deleteSalaryPayment(String transactionId) {
		String sql = "DELETE FROM SalaryPayment WHERE transactionId = ?";
		template.update(sql, transactionId);
	}

	public SalaryPayment getSalaryPayment(String transactionId) {
		try {
			String sql = "SELECT * FROM SalaryPayment WHERE transactionId = ?";
			return template.queryForObject(sql, new RowMapper<SalaryPayment>() {

				public SalaryPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
					Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
					SalaryPayment salaryPayment = (new BeanPropertyRowMapper<>(SalaryPayment.class)).mapRow(rs, rowNum);
					salaryPayment.setEmployee(employee);
					return salaryPayment;
				}

			}, new Object[] { transactionId });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<SalaryPayment> getAll() {
		String sql = "SELECT * FROM SalaryPayment NATURAL JOIN Employee NATURAL JOIN User";

		return template.query(sql, new RowMapper<SalaryPayment>() {

			public SalaryPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
				Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
				SalaryPayment salaryPayment = (new BeanPropertyRowMapper<>(SalaryPayment.class)).mapRow(rs, rowNum);

				employee.setUser(user);
				salaryPayment.setEmployee(employee);

				return salaryPayment;
			}

		});
	}

	public List<SalaryPayment> getSalaryPaymentsByEmployeeId(String employeeId) {
		String sql = "SELECT * FROM SalaryPayment WHERE employeeId = ?";

		return template.query(sql, new BeanPropertyRowMapper<>(SalaryPayment.class), new Object[] { employeeId });
	}
}
