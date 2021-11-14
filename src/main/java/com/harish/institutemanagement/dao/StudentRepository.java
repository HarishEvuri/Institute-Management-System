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

import com.harish.institutemanagement.models.Student;
import com.harish.institutemanagement.models.User;

@Repository
public class StudentRepository {

	@Autowired
	private JdbcTemplate template;

	public void createStudent(Student student) {
		String sql = "INSERT INTO Student (rollNumber, joinYear, caste, familyIncome, gaurdianName, gaurdianRelation, gaurdianPhoneNumber, username, departmentId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, student.getRollNumber(), student.getJoinYear(), student.getCaste(),
				student.getFamilyIncome(), student.getGaurdianName(), student.getGaurdianRelation(),
				student.getGaurdianPhoneNumber(), student.getUser().getUsername(), student.getDepartmentId());

	}

	public Student getStudentByUsername(String username) {
		String sql = "SELECT * FROM Student WHERE username = ?";

		try {
			return template.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), new Object[] { username });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Student getStudentByRollNumber(String rollNumber) {
		String sql = "SELECT * FROM Student WHERE rollNumber = ?";

		try {
			return template.queryForObject(sql, new RowMapper<Student>() {

				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

					User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
					Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);

					student.setUser(user);
					return student;
				}
			}, new Object[] { rollNumber });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updateStudent(Student student) {
		String sql = "UPDATE Student SET joinYear = ?, caste = ?, familyIncome = ?, gaurdianName = ?, gaurdianRelation = ?, gaurdianPhoneNumber = ?, departmentId = ? WHERE rollNumber = ?";
		template.update(sql, student.getJoinYear(), student.getCaste(), student.getFamilyIncome(),
				student.getGaurdianName(), student.getGaurdianRelation(), student.getGaurdianPhoneNumber(),
				student.getDepartmentId(), student.getRollNumber());
	}

	public List<Student> getAll() {
		String sql = "SELECT * FROM Student NATURAL JOIN User";
		return template.query(sql, new RowMapper<Student>() {

			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

				Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
				student.setUser(user);
				return student;
			}

		});
	}

}
