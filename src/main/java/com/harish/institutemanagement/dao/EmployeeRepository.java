package com.harish.institutemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.Employee;
import com.harish.institutemanagement.models.User;

@Repository
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate template;

	public void createEmployee(Employee employee) {
		String sql = "INSERT INTO Employee (employeeId, joinDate, endDate, accountNumber, bank_IFSC_code, panNumber, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, employee.getEmployeeId(), employee.getJoinDate(), employee.getEndDate(),
				employee.getAccountNumber(), employee.getBank_IFSC_code(), employee.getPanNumber(),
				employee.getUser().getUsername());
	}

	public void updateEmployee(Employee employee) {
		String sql = "UPDATE Employee SET joinDate = ?, endDate = ?, accountNumber = ?, bank_IFSC_code = ?, panNumber = ? WHERE employeeId = ?";
		template.update(sql, employee.getJoinDate(), employee.getEndDate(), employee.getAccountNumber(),
				employee.getBank_IFSC_code(), employee.getPanNumber(), employee.getEmployeeId());
	}

	public List<Employee> getAll() {
		String sql = "SELECT * FROM Employee NATURAL JOIN User";
		return template.query(sql, new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
				Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);

				employee.setUser(user);
				return employee;
			}

		});
	}
}
