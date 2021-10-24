package com.harish.institutemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.Department;

@Repository
public class DepartmentRepository {

	@Autowired
	private JdbcTemplate template;

	public void createDepartment(Department department) {
		String sql = "INSERT INTO Department (departmentId, departmentName, address) VALUES (?, ?, ?)";
		template.update(sql, department.getDepartmentId(), department.getDepartmentName(), department.getAddress());
	}

	public void updateDepartment(Department department) {
		String sql = "UPDATE Department SET departmentName = ?, address = ? WHERE departmentId = ?";
		template.update(sql, department.getDepartmentName(), department.getAddress(), department.getDepartmentId());
	}

	public void deleteDepartment(String departmentId) {
		String sql = "DELETE FROM Department WHERE departmentId = ?";
		template.update(sql, departmentId);
	}

	public List<Department> getAll() {
		String sql = "SELECT * FROM Department";
		return template.query(sql, new BeanPropertyRowMapper<>(Department.class));
	}

}
