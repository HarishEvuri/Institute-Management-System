package com.harish.institutemanagement.controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.harish.institutemanagement.dao.ComplaintRepository;
import com.harish.institutemanagement.dao.CourseRepository;
import com.harish.institutemanagement.dao.DepartmentRepository;
import com.harish.institutemanagement.dao.EmployeeRepository;
import com.harish.institutemanagement.dao.EnrollmentRepository;
import com.harish.institutemanagement.dao.FeePaymentRepository;
import com.harish.institutemanagement.dao.HODRepository;
import com.harish.institutemanagement.dao.ProfessorRepository;
import com.harish.institutemanagement.dao.SectionRepository;
import com.harish.institutemanagement.dao.SectionTimingRepository;
import com.harish.institutemanagement.dao.StudentRepository;
import com.harish.institutemanagement.dao.UserPhoneNumberRepository;
import com.harish.institutemanagement.dao.UserRepository;
import com.harish.institutemanagement.models.Complaint;
import com.harish.institutemanagement.models.Course;
import com.harish.institutemanagement.models.Department;
import com.harish.institutemanagement.models.Employee;
import com.harish.institutemanagement.models.Enrollment;
import com.harish.institutemanagement.models.FeePayment;
import com.harish.institutemanagement.models.HOD;
import com.harish.institutemanagement.models.Professor;
import com.harish.institutemanagement.models.Section;
import com.harish.institutemanagement.models.SectionTiming;
import com.harish.institutemanagement.models.Student;
import com.harish.institutemanagement.models.User;
import com.harish.institutemanagement.models.UserPhoneNumber;
import com.harish.institutemanagement.services.SecurityService;

@Controller
@Transactional
public class StudentController {

	@Autowired
	SecurityService securityService;

	@Autowired
	UserPhoneNumberRepository userPhoneNumberRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	HODRepository hodRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	FeePaymentRepository feePaymentRepository;

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	SectionTimingRepository sectionTimingRepository;

	@Autowired
	ComplaintRepository complaintRepository;

	@GetMapping("/student/dashboard")
	public String getDashboard() {

		return "student/dashboard";
	}

	@GetMapping("/student/profile")
	public String getProfile(Model model) {

		User user = securityService.findLoggedInUser();
		List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberRepository
				.getPhoneNumbersByUsername(user.getUsername());
		Student student = studentRepository.getStudentByUsername(user.getUsername());

		model.addAttribute("user", user);
		model.addAttribute("userPhoneNumbers", userPhoneNumbers);
		model.addAttribute("student", student);

		return "student/profile";
	}

	@GetMapping("/student/departments")
	public String getDepartments(Model model) {

		List<Department> departments = departmentRepository.getAll();
		model.addAttribute("departments", departments);
		return "student/departments";
	}

	@GetMapping("/student/departments/{departmentId}")
	public String getDepartment(@PathVariable("departmentId") String departmentId, Model model) {

		Department department = departmentRepository.getDepartment(departmentId);
		if (department == null)
			return "redirect:/student/departments";

		HOD currentHOD = hodRepository.getCurrentHOD(departmentId);
		List<Professor> professors = professorRepository.getProfessorByDepartment(departmentId);

		model.addAttribute("department", department);
		model.addAttribute("currentHOD", currentHOD);
		model.addAttribute("professors", professors);
		return "student/viewDepartment";
	}

	@GetMapping("/student/students")
	public String getStudents(Model model) {

		List<Student> students = studentRepository.getAll();
		model.addAttribute("students", students);

		return "student/students";
	}

	@GetMapping("/student/courses")
	public String getCourses(Model model) {

		List<Course> courses = courseRepository.getAll();
		model.addAttribute("courses", courses);
		return "student/courses";
	}

	@GetMapping("/student/fee-transactions")
	public String getTransactions(Model model) {

		User user = securityService.findLoggedInUser();
		Student student = studentRepository.getStudentByUsername(user.getUsername());
		List<FeePayment> feePayments = feePaymentRepository.getFeePaymentsByRollNumber(student.getRollNumber());
		model.addAttribute("feePayments", feePayments);
		return "student/feeTransactions";
	}

	@GetMapping("/student/enrollments")
	public String getEnrollments(Model model) {

		User user = securityService.findLoggedInUser();
		Student student = studentRepository.getStudentByUsername(user.getUsername());
		List<Enrollment> enrollments = enrollmentRepository.getEnrollmentsByRollNumber(student.getRollNumber());

		model.addAttribute("enrollments", enrollments);
		return "student/enrollments";
	}

	@GetMapping("/student/enrollments/{courseId}/{sectionId}")
	public String getEnrollment(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			Model model) {

		List<Enrollment> enrollments = enrollmentRepository.getEnrollmentsBySection(sectionId, courseId);
		Section section = sectionRepository.getSection(sectionId, courseId);
		Course course = courseRepository.getCourseByCourseId(courseId);
		Professor professor = professorRepository.getProfessorByProfessorId(section.getProfessor().getProfessorId());
		Employee employee = employeeRepository.getEmployeeByEmployeeId(professor.getEmployee().getEmployeeId());
		User user = userRepository.getUser(employee.getUser().getUsername());
		List<SectionTiming> sectionTimings = sectionTimingRepository.getSectionTimings(sectionId, courseId);

		employee.setUser(user);
		professor.setEmployee(employee);
		section.setCourse(course);
		section.setProfessor(professor);

		model.addAttribute("enrollments", enrollments);
		model.addAttribute("section", section);
		model.addAttribute("sectionTimings", sectionTimings);
		return "student/viewEnrollment";
	}

	@GetMapping("/student/complaints")
	public String getComplaints(Model model) {

		User user = securityService.findLoggedInUser();
		Student student = studentRepository.getStudentByUsername(user.getUsername());
		List<Complaint> complaints = complaintRepository.getComplaintsByRollNumber(student.getRollNumber());

		model.addAttribute("complaints", complaints);
		return "student/complaints";
	}

	@GetMapping("/student/complaints/{complaintId}")
	public String getComplaint(@PathVariable("complaintId") String complaintId, Model model) {

		User user = securityService.findLoggedInUser();
		Student student = studentRepository.getStudentByUsername(user.getUsername());
		Complaint complaint = complaintRepository.getComplaint(complaintId);

		if (!student.getRollNumber().equals(complaint.getStudent().getRollNumber()))
			return "redirect:/student/complaints";

		model.addAttribute("complaint", complaint);
		return "student/viewComplaint";
	}

	@GetMapping("/student/complaints/{complaintId}/delete")
	public String deleteComplaint(@PathVariable("complaintId") String complaintId, Model model) {

		User user = securityService.findLoggedInUser();
		Student student = studentRepository.getStudentByUsername(user.getUsername());
		Complaint complaint = complaintRepository.getComplaint(complaintId);

		if (!student.getRollNumber().equals(complaint.getStudent().getRollNumber()))
			return "redirect:/student/complaints";

		complaintRepository.deleteComplaint(complaintId);
		return "redirect:/student/complaints";
	}

	@GetMapping("/student/complaints/add")
	public String addComplaint(Model model) {
		Complaint complaint = new Complaint();
		model.addAttribute("complaint", complaint);
		model.addAttribute("submiturl", "/student/complaints/add");
		return "student/addEditComplaint";
	}

	@PostMapping("/student/complaints/add")
	public String addComplaint(@Valid @ModelAttribute("complaint") Complaint complaint, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("submiturl", "/student/complaints/add");
			return "student/addEditComplaint";
		}

		User user = securityService.findLoggedInUser();
		Student student = studentRepository.getStudentByUsername(user.getUsername());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		complaint.setStudent(student);
		complaint.setTimestamp(timestamp);

		complaintRepository.createComplaint(complaint);
		return "redirect:/student/complaints";
	}

}
