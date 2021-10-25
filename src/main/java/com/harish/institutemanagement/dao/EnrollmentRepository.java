package com.harish.institutemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.Course;
import com.harish.institutemanagement.models.Enrollment;
import com.harish.institutemanagement.models.Section;
import com.harish.institutemanagement.models.Student;

@Repository
public class EnrollmentRepository {

	@Autowired
	private JdbcTemplate template;

	public void createEnrollment(Enrollment enrollment) {
		String sql = "INSERT INTO Enrollment (rollNumber, sectionId, courseId, grade, attendance, feedback) VALUES (?, ?, ?, ?, ?, ?)";
		template.update(sql, enrollment.getStudent().getRollNumber(), enrollment.getSection().getSectionId(),
				enrollment.getSection().getCourse().getCourseId(), enrollment.getFeedback());
	}

	public void updateEnrollment(Enrollment enrollment) {
		String sql = "UPDATE Enrollment SET grade = ?, attendance = ?, feedback = ? WHERE rollNumber = ? AND sectionId = ? AND courseId = ?";
		template.update(sql, enrollment.getGrade(), enrollment.getAttendance(), enrollment.getFeedback(),
				enrollment.getStudent().getRollNumber(), enrollment.getSection().getSectionId(),
				enrollment.getSection().getCourse().getCourseId());
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
		String sql = "SELECT * FROM Enrollment NATURAL JOIN Student WHERE sectionId = ? AND courseId = ?";
		return template.query(sql, new RowMapper<Enrollment>() {

			public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {

				Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
				Enrollment enrollment = (new BeanPropertyRowMapper<>(Enrollment.class)).mapRow(rs, rowNum);

				enrollment.setStudent(student);
				return enrollment;
			}

		}, new Object[] { sectionId, courseId });
	}
}
