package com.harish.institutemanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.Salary;

@Repository
public class SalaryRepository {

	@Autowired
	private JdbcTemplate template;

	public void updateSalary(Salary salary) {
		String sql = "UPDATE Salary SET amount = ? WHERE salaryId = ?";
		template.update(sql, salary.getAmount(), salary.getSalaryId());
	}

	public void createSalary(Salary salary) {
		String sql = "INSERT INTO Salary (salaryId, role, experience, amount) VALUES (?, ?, ?, ?)";
		template.update(sql, salary.getSalaryId(), salary.getRole(), salary.getExperience(), salary.getAmount());
	}

}
