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

import com.harish.institutemanagement.models.Course;
import com.harish.institutemanagement.models.Enrollment;
import com.harish.institutemanagement.models.Section;
import com.harish.institutemanagement.models.Student;
import com.harish.institutemanagement.models.User;

@Repository
public class EnrollmentRepository {

	@Autowired
	private JdbcTemplate template;

	public void createEnrollment(String courseId, String sectionId, String rollNumber) {
		String sql = "INSERT INTO Enrollment (rollNumber, sectionId, courseId) VALUES (?, ?, ?)";
		template.update(sql, rollNumber, sectionId, courseId);
	}

	public void updateEnrollment(Enrollment enrollment, String courseId, String sectionId, String rollNumber) {
		String sql = "UPDATE Enrollment SET grade = ?, attendance = ? WHERE rollNumber = ? AND sectionId = ? AND courseId = ?";
		template.update(sql, enrollment.getGrade(), enrollment.getAttendance(), rollNumber, sectionId, courseId);
	}

	public void deleteEnrollment(String courseId, String sectionId, String rollNumber) {
		String sql = "DELETE FROM Enrollment WHERE courseId = ? AND sectionId = ? AND rollNumber = ?";
		template.update(sql, courseId, sectionId, rollNumber);
	}

	public Enrollment getEnrollment(String courseId, String sectionId, String rollNumber) {
		try {
			String sql = "SELECT * FROM Enrollment WHERE courseId = ? AND sectionId = ? AND rollNumber = ?";
			return template.queryForObject(sql, new BeanPropertyRowMapper<>(Enrollment.class),
					new Object[] { courseId, sectionId, rollNumber });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Enrollment> getEnrollmentsByRollNumber(String rollNumber) {
		String sql = "SELECT * FROM Enrollment NATURAL JOIN Section NATURAL JOIN Course WHERE rollNumber = ?";
		return template.query(sql, new RowMapper<Enrollment>() {

			public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);
				Section section = (new BeanPropertyRowMapper<>(Section.class)).mapRow(rs, rowNum);
				Enrollment enrollment = (new BeanPropertyRowMapper<>(Enrollment.class)).mapRow(rs, rowNum);

				section.setCourse(course);
				enrollment.setSection(section);
				return enrollment;
			}

		}, new Object[] { rollNumber });
	}

	public List<Enrollment> getEnrollmentsBySection(String sectionId, String courseId) {
		String sql = "SELECT * FROM Enrollment NATURAL JOIN Student NATURAL JOIN User WHERE sectionId = ? AND courseId = ?";
		return template.query(sql, new RowMapper<Enrollment>() {

			public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
				Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
				Enrollment enrollment = (new BeanPropertyRowMapper<>(Enrollment.class)).mapRow(rs, rowNum);

				student.setUser(user);
				enrollment.setStudent(student);
				return enrollment;
			}

		}, new Object[] { sectionId, courseId });
	}
}
