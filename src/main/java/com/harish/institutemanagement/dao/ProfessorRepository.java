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
import com.harish.institutemanagement.models.Professor;
import com.harish.institutemanagement.models.User;

@Repository
public class ProfessorRepository {

	@Autowired
	private JdbcTemplate template;

	public void createProfessor(Professor professor) {
		String sql = "INSERT INTO Professor (professorId, qualification, employeeId, departmentId) VALUES (?, ?, ?, ?)";
		template.update(sql, professor.getProfessorId(), professor.getQualification(),
				professor.getEmployee().getEmployeeId(), professor.getDepartmentId());
	}

	public void updateProfessor(Professor professor) {
		String sql = "UPDATE Professor SET qualification = ?, departmentId = ? WHERE professorId = ?";
		template.update(sql, professor.getQualification(), professor.getDepartmentId(), professor.getProfessorId());
	}

	public List<Professor> getAll() {
		String sql = "SELECT * FROM Professor NATURAL JOIN Employee NATURAL JOIN User";
		return template.query(sql, new RowMapper<Professor>() {

			public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
				Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
				Professor professor = (new BeanPropertyRowMapper<>(Professor.class)).mapRow(rs, rowNum);

				employee.setUser(user);
				professor.setEmployee(employee);
				return professor;
			}

		});
	}

	public List<Professor> getProfessorsByDepartmentId(String departmentId) {
		String sql = "SELECT * FROM Professor NATURAL JOIN Employee NATURAL JOIN User WHERE departmentId = ?";

		return template.query(sql, new RowMapper<Professor>() {

			public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
				Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
				Professor professor = (new BeanPropertyRowMapper<>(Professor.class)).mapRow(rs, rowNum);

				employee.setUser(user);
				professor.setEmployee(employee);
				return professor;
			}

		}, new Object[] { departmentId });
	}
}
