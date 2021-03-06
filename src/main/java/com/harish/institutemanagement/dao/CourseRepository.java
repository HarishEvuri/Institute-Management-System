package com.harish.institutemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.Course;

@Repository
public class CourseRepository {

	@Autowired
	JdbcTemplate template;

	public void createCourse(Course course) {
		String sql = "INSERT INTO Course (courseId, name, credits, departmentId) VALUES (?, ?, ?, ?)";
		template.update(sql, course.getCourseId(), course.getName(), course.getCredits(), course.getDepartmentId());
	}

	public void updateCourse(Course course) {
		String sql = "UPDATE Course SET name = ?, credits = ?, departmentId = ? WHERE courseId = ?";
		template.update(sql, course.getName(), course.getCredits(), course.getDepartmentId(), course.getCourseId());
	}

	public void deleteCourse(String courseId) {
		String sql = "DELETE FROM Course WHERE courseId = ?";
		template.update(sql, courseId);
	}

	public List<Course> getAll() {
		String sql = "SELECT * FROM Course";
		return template.query(sql, new BeanPropertyRowMapper<>(Course.class));
	}

	public Course getCourseByCourseId(String courseId) {
		try {
			String sql = "SELECT * FROM Course WHERE courseId = ?";
			return template.queryForObject(sql, new BeanPropertyRowMapper<>(Course.class), new Object[] { courseId });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
