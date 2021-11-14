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
import com.harish.institutemanagement.models.Staff;
import com.harish.institutemanagement.models.User;

@Repository
public class StaffRepository {

	@Autowired
	private JdbcTemplate template;

	public void createStaff(Staff staff) {
		String sql = "INSERT INTO Staff (staffId, designation, employeeId) VALUES (?, ?, ?)";
		template.update(sql, staff.getStaffId(), staff.getDesignation(), staff.getEmployee().getEmployeeId());
	}

	public Staff getStaffByEmployeeId(String employeeId) {
		String sql = "SELECT * FROM Staff WHERE employeeId = ?";

		try {
			return template.queryForObject(sql, new BeanPropertyRowMapper<>(Staff.class), new Object[] { employeeId });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Staff getStaffByStaffId(String staffId) {
		String sql = "SELECT * FROM Staff WHERE staffId = ?";

		try {
			return template.queryForObject(sql, new RowMapper<Staff>() {

				public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {

					Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
					Staff staff = (new BeanPropertyRowMapper<>(Staff.class)).mapRow(rs, rowNum);

					staff.setEmployee(employee);
					return staff;
				}
			}, new Object[] { staffId });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updateStaff(Staff staff) {
		String sql = "UPDATE Staff SET designation = ? WHERE staffId = ?";
		template.update(sql, staff.getDesignation(), staff.getStaffId());
	}

	public List<Staff> getAll() {
		String sql = "SELECT * FROM Staff NATURAL JOIN Employee NATURAL JOIN User";
		return template.query(sql, new RowMapper<Staff>() {

			public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

				Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);

				Staff staff = (new BeanPropertyRowMapper<>(Staff.class)).mapRow(rs, rowNum);

				employee.setUser(user);
				staff.setEmployee(employee);
				return staff;
			}

		});

	}
}
