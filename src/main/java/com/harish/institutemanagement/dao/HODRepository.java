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
import com.harish.institutemanagement.models.HOD;
import com.harish.institutemanagement.models.Professor;
import com.harish.institutemanagement.models.User;

@Repository
public class HODRepository {

	@Autowired
	private JdbcTemplate template;

	public void createHOD(HOD hod) {
		String sql = "INSERT INTO HOD (departmentId, professorId, startDate, endDate) VALUES (?, ?, ?, ?)";
		template.update(sql, hod.getDepartmentId(), hod.getProfessor().getProfessorId(), hod.getStartDate(),
				hod.getEndDate());
	}

	public void deleteHOD(HOD hod) {
		String sql = "DELETE FROM HOD WHERE departmentId = ? AND professorId = ? AND startDate = ?";
		template.update(sql, hod.getDepartmentId(), hod.getProfessor().getProfessorId(), hod.getStartDate());
	}

	public List<HOD> getHODsByDepartmentId(String departmentId) {

		String sql = "SELECT * FROM HOD NATURL JOIN Professor NATURAL JOIN Employee NATURAL JOIN User WHERE departmentId = ?";
		return template.query(sql, new RowMapper<HOD>() {

			public HOD mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
				Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
				Professor professor = (new BeanPropertyRowMapper<>(Professor.class)).mapRow(rs, rowNum);
				HOD hod = (new BeanPropertyRowMapper<>(HOD.class)).mapRow(rs, rowNum);

				employee.setUser(user);
				professor.setEmployee(employee);
				hod.setProfessor(professor);
				return hod;
			}

		}, new Object[] { departmentId });
	}
}
