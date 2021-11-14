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
import com.harish.institutemanagement.models.Employee;
import com.harish.institutemanagement.models.Professor;
import com.harish.institutemanagement.models.Section;
import com.harish.institutemanagement.models.User;

@Repository
public class SectionRepository {

	@Autowired
	private JdbcTemplate template;

	public void createSection(Section section) {
		String sql = "INSERT INTO Section (sectionId, courseId, semester, year, roomNumber, professorId) VALUES (?, ?, ?, ?, ?, ?)";
		template.update(sql, section.getSectionId(), section.getCourse().getCourseId(), section.getSemester(),
				section.getYear(), section.getRoomNumber(), section.getProfessor().getProfessorId());
	}

	public void updateSection(Section section) {
		String sql = "UPDATE Section SET semester = ?, year = ?, roomNumber = ?, professorId = ?, isLocked = ? WHERE sectionId = ? AND courseId = ?";
		template.update(sql, section.getSemester(), section.getYear(), section.getRoomNumber(),
				section.getProfessor().getProfessorId(), section.getIsLocked(), section.getSectionId(),
				section.getCourse().getCourseId());
	}

	public void deleteSection(String sectionId, String courseId) {
		String sql = "DELETE FROM Section WHERE sectionId = ? AND courseId = ?";
		template.update(sql, sectionId, courseId);
	}

	public Section getSection(String sectionId, String courseId) {
		try {
			String sql = "SELECT * FROM Section WHERE courseId = ? AND sectionId = ?";
			return template.queryForObject(sql, new RowMapper<Section>() {

				public Section mapRow(ResultSet rs, int rowNum) throws SQLException {
					Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);
					Professor professor = (new BeanPropertyRowMapper<>(Professor.class)).mapRow(rs, rowNum);
					Section section = (new BeanPropertyRowMapper<>(Section.class)).mapRow(rs, rowNum);

					section.setCourse(course);
					section.setProfessor(professor);
					return section;
				}

			}, new Object[] { courseId, sectionId });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Section> getAll() {
		String sql = "SELECT * FROM Section";
		return template.query(sql, new RowMapper<Section>() {

			public Section mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);
				Section section = (new BeanPropertyRowMapper<>(Section.class)).mapRow(rs, rowNum);
				Professor professor = (new BeanPropertyRowMapper<>(Professor.class)).mapRow(rs, rowNum);

				section.setCourse(course);
				section.setProfessor(professor);
				return section;
			}

		});
	}

	public List<Section> getSectionsByCourseId(String courseId) {
		String sql = "SELECT * FROM Section NATURAL JOIN Professor NATURAL JOIN Employee NATURL JOIN User WHERE courseId = ?";
		return template.query(sql, new RowMapper<Section>() {

			public Section mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
				Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
				Professor professor = (new BeanPropertyRowMapper<>(Professor.class)).mapRow(rs, rowNum);
				Section section = (new BeanPropertyRowMapper<>(Section.class)).mapRow(rs, rowNum);

				employee.setUser(user);
				professor.setEmployee(employee);
				section.setProfessor(professor);
				return section;
			}

		}, new Object[] { courseId });
	}

	public List<Section> getSectionsByProfessorId(String professorId) {
		String sql = "SELECT * FROM Section NATURAL JOIN Course WHERE professorId = ?";

		return template.query(sql, new RowMapper<Section>() {

			public Section mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);
				Section section = (new BeanPropertyRowMapper<>(Section.class)).mapRow(rs, rowNum);

				section.setCourse(course);
				return section;
			}

		}, new Object[] { professorId });
	}
}
