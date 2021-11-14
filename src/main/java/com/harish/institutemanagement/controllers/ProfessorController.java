package com.harish.institutemanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.harish.institutemanagement.dao.CourseRepository;
import com.harish.institutemanagement.dao.DepartmentRepository;
import com.harish.institutemanagement.dao.EmployeeRepository;
import com.harish.institutemanagement.dao.EnrollmentRepository;
import com.harish.institutemanagement.dao.HODRepository;
import com.harish.institutemanagement.dao.ProfessorRepository;
import com.harish.institutemanagement.dao.SalaryPaymentRepository;
import com.harish.institutemanagement.dao.SectionRepository;
import com.harish.institutemanagement.dao.SectionTimingRepository;
import com.harish.institutemanagement.dao.StudentRepository;
import com.harish.institutemanagement.dao.UserPhoneNumberRepository;
import com.harish.institutemanagement.models.Course;
import com.harish.institutemanagement.models.Department;
import com.harish.institutemanagement.models.Employee;
import com.harish.institutemanagement.models.Enrollment;
import com.harish.institutemanagement.models.HOD;
import com.harish.institutemanagement.models.Professor;
import com.harish.institutemanagement.models.SalaryPayment;
import com.harish.institutemanagement.models.Section;
import com.harish.institutemanagement.models.SectionTiming;
import com.harish.institutemanagement.models.Student;
import com.harish.institutemanagement.models.User;
import com.harish.institutemanagement.models.UserPhoneNumber;
import com.harish.institutemanagement.services.SecurityService;

@Controller
@Transactional
public class ProfessorController {

	@Autowired
	SecurityService securityService;

	@Autowired
	UserPhoneNumberRepository userPhoneNumberRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	HODRepository hodRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	SalaryPaymentRepository salaryPaymentRepository;

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	SectionTimingRepository sectionTimingRepository;

	@GetMapping("/professor/dashboard")
	public String getDashboard() {

		return "professor/dashboard";
	}

	@GetMapping("/professor/profile")
	public String getProfile(Model model) {

		User user = securityService.findLoggedInUser();
		List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberRepository
				.getPhoneNumbersByUsername(user.getUsername());
		Employee employee = employeeRepository.getEmployeeByUsername(user.getUsername());

		if (employee == null)
			return "redirect:/professor/dashboard";

		Professor professor = professorRepository.getProfessorByEmployeeId(employee.getEmployeeId());

		model.addAttribute("user", user);
		model.addAttribute("userPhoneNumbers", userPhoneNumbers);
		model.addAttribute("employee", employee);
		model.addAttribute("professor", professor);

		return "professor/profile";
	}

	@GetMapping("/professor/departments")
	public String getDepartments(Model model) {

		List<Department> departments = departmentRepository.getAll();
		model.addAttribute("departments", departments);
		return "professor/departments";
	}

	@GetMapping("/professor/departments/{departmentId}")
	public String getDepartment(@PathVariable("departmentId") String departmentId, Model model) {

		Department department = departmentRepository.getDepartment(departmentId);
		if (department == null)
			return "redirect:/professor/departments";

		HOD currentHOD = hodRepository.getCurrentHOD(departmentId);
		List<Professor> professors = professorRepository.getProfessorByDepartment(departmentId);

		model.addAttribute("department", department);
		model.addAttribute("currentHOD", currentHOD);
		model.addAttribute("professors", professors);
		return "professor/viewDepartment";
	}

	@GetMapping("/professor/professors")
	public String getProfessors(Model model) {

		List<Professor> professors = professorRepository.getAll();
		model.addAttribute("professors", professors);

		return "professor/professors";
	}

	@GetMapping("/professor/students")
	public String getStudents(Model model) {

		List<Student> students = studentRepository.getAll();
		model.addAttribute("students", students);

		return "professor/students";
	}

	@GetMapping("/professor/courses")
	public String getCourses(Model model) {

		List<Course> courses = courseRepository.getAll();
		model.addAttribute("courses", courses);
		return "professor/courses";
	}

	@GetMapping("/professor/salary-transactions")
	public String getTransactions(Model model) {

		User user = securityService.findLoggedInUser();
		Employee employee = employeeRepository.getEmployeeByUsername(user.getUsername());

		List<SalaryPayment> salaryPayments = salaryPaymentRepository
				.getSalaryPaymentsByEmployeeId(employee.getEmployeeId());
		model.addAttribute("salaryPayments", salaryPayments);
		return "professor/salaryTransactions";
	}

	@GetMapping("/professor/sections")
	public String getSections(Model model) {

		User user = securityService.findLoggedInUser();
		Employee employee = employeeRepository.getEmployeeByUsername(user.getUsername());
		Professor professor = professorRepository.getProfessorByEmployeeId(employee.getEmployeeId());

		List<Section> sections = sectionRepository.getSectionsByProfessorId(professor.getProfessorId());
		model.addAttribute("sections", sections);
		return "professor/sections";
	}

	@GetMapping("/professor/sections/{courseId}/{sectionId}")
	public String getSection(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			Model model) {

		User user = securityService.findLoggedInUser();
		Employee employee = employeeRepository.getEmployeeByUsername(user.getUsername());
		Professor professor = professorRepository.getProfessorByEmployeeId(employee.getEmployeeId());
		Section section = sectionRepository.getSection(sectionId, courseId);

		if (!section.getProfessor().getProfessorId().equals(professor.getProfessorId()))
			return "redirect:/professor/sections";

		Course course = courseRepository.getCourseByCourseId(courseId);
		List<SectionTiming> sectionTimings = sectionTimingRepository.getSectionTimings(sectionId, courseId);

		employee.setUser(user);
		professor.setEmployee(employee);
		section.setProfessor(professor);
		section.setCourse(course);

		List<Enrollment> enrollments = enrollmentRepository.getEnrollmentsBySection(sectionId, courseId);
		model.addAttribute("section", section);
		model.addAttribute("sectionTimings", sectionTimings);
		model.addAttribute("enrollments", enrollments);
		return "professor/viewSection";
	}

	@GetMapping("/professor/enrollments/{courseId}/{sectionId}/{rollNumber}/edit")
	public String editEnrollment(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			@PathVariable("rollNumber") String rollNumber, Model model) {

		User user = securityService.findLoggedInUser();
		Employee employee = employeeRepository.getEmployeeByUsername(user.getUsername());
		Professor professor = professorRepository.getProfessorByEmployeeId(employee.getEmployeeId());
		Section section = sectionRepository.getSection(sectionId, courseId);
		Enrollment enrollment = enrollmentRepository.getEnrollment(courseId, sectionId, rollNumber);

		if (!section.getProfessor().getProfessorId().equals(professor.getProfessorId()))
			return "redirect:/professor/sections";

		if (enrollment == null || section.getIsLocked())
			return "redirect:/professor/sections/" + courseId + "/" + sectionId;

		model.addAttribute("courseId", courseId);
		model.addAttribute("sectionId", sectionId);
		model.addAttribute("rollNumber", rollNumber);
		model.addAttribute("enrollment", enrollment);
		model.addAttribute("submiturl",
				"/professor/enrollments/" + courseId + "/" + sectionId + "/" + rollNumber + "/edit");
		return "professor/addEditEnrollment";
	}

	@PostMapping("/professor/enrollments/{courseId}/{sectionId}/{rollNumber}/edit")
	public String editEnrollment(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			@PathVariable("rollNumber") String rollNumber, @ModelAttribute("enrollment") Enrollment enrollment,
			Model model) {

		Section section = sectionRepository.getSection(sectionId, courseId);
		User user = securityService.findLoggedInUser();
		Employee employee = employeeRepository.getEmployeeByUsername(user.getUsername());
		Professor professor = professorRepository.getProfessorByEmployeeId(employee.getEmployeeId());

		if (!section.getProfessor().getProfessorId().equals(professor.getProfessorId()))
			return "redirect:/professor/sections";

		if (section.getIsLocked())
			return "redirect:/professor/sections/" + courseId + "/" + sectionId;

		enrollmentRepository.updateEnrollment(enrollment, courseId, sectionId, rollNumber);
		return "redirect:/professor/sections/" + courseId + "/" + sectionId;
	}
}
