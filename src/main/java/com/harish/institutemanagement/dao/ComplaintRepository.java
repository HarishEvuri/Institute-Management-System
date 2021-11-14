package com.harish.institutemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harish.institutemanagement.models.Complaint;
import com.harish.institutemanagement.models.Student;
import com.harish.institutemanagement.models.User;

@Repository
public class ComplaintRepository {

	@Autowired
	private JdbcTemplate template;

	public void createComplaint(Complaint complaint) {
		String sql = "INSERT INTO Complaint (complaintId, rollNumber, timestamp, subject, description, response) VALUES (?, ?, ?, ?, ?, ?)";
		template.update(sql, complaint.getComplaintId(), complaint.getStudent().getRollNumber(),
				complaint.getTimestamp(), complaint.getSubject(), complaint.getDescription(), complaint.getResponse());
	}

	public void updateComplaint(Complaint complaint) {
		String sql = "UPDATE Complaint SET response = ? WHERE complaintId = ?";
		template.update(sql, complaint.getResponse(), complaint.getComplaintId());
	}

	public Complaint getComplaint(String complaintId) {
		String sql = "SELECT * FROM Complaint WHERE complaintId = ?";
		return template.queryForObject(sql, new RowMapper<Complaint>() {

			public Complaint mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
				Complaint complaint = (new BeanPropertyRowMapper<>(Complaint.class)).mapRow(rs, rowNum);
				complaint.setStudent(student);
				return complaint;
			}

		}, new Object[] { complaintId });
	}

	public void deleteComplaint(String complaintId) {
		String sql = "DELETE FROM Complaint WHERE complaintId = ?";
		template.update(sql, complaintId);
	}

	public List<Complaint> getAll() {
		String sql = "SELECT * FROM Complaint NATURAL JOIN Student NATURAL JOIN User";

		return template.query(sql, new RowMapper<Complaint>() {

			public Complaint mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
				Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
				Complaint complaint = (new BeanPropertyRowMapper<>(Complaint.class)).mapRow(rs, rowNum);

				student.setUser(user);
				complaint.setStudent(student);
				return complaint;
			}

		});
	}

	public List<Complaint> getComplaintsByRollNumber(String rollNumber) {
		String sql = "SELECT * FROM Complaint WHERE rollNumber = ?";

		return template.query(sql, new BeanPropertyRowMapper<>(Complaint.class), new Object[] { rollNumber });
	}

}
