package com.harish.institutemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.SectionTiming;

@Repository
public class SectionTimingRepository {

	@Autowired
	private JdbcTemplate template;

	public void addSectionTiming(SectionTiming sectionTiming) {
		String sql = "INSERT INTO SectionTiming (sectionId, courseId, day, slot) VALUES (?, ?, ?, ?)";
		template.update(sql, sectionTiming.getSectionId(), sectionTiming.getCourseId(), sectionTiming.getDay(),
				sectionTiming.getSlot());
	}

	public SectionTiming getSectionTiming(SectionTiming sectionTiming) {
		try {
			String sql = "SELECT * FROM SectionTiming WHERE sectionId = ? AND courseId = ? AND day = ? AND slot = ?";
			return template.queryForObject(sql, new BeanPropertyRowMapper<>(SectionTiming.class),
					new Object[] { sectionTiming.getSectionId(), sectionTiming.getCourseId(), sectionTiming.getDay(),
							sectionTiming.getSlot() });
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void deleteSectionTiming(SectionTiming sectionTiming) {
		String sql = "DELETE FROM SectionTiming WHERE sectionId = ? AND courseId = ? AND day = ? AND slot = ?";
		template.update(sql, sectionTiming.getSectionId(), sectionTiming.getCourseId(), sectionTiming.getDay(),
				sectionTiming.getSlot());
	}

	public List<SectionTiming> getSectionTimings(String sectionId, String courseId) {
		String sql = "SELECT * FROM SectionTiming WHERE sectionId = ? AND courseId = ?";
		return template.query(sql, new BeanPropertyRowMapper<>(SectionTiming.class),
				new Object[] { sectionId, courseId });
	}

}
