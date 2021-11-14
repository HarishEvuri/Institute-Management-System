package com.harish.institutemanagement.controllers;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

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
import com.harish.institutemanagement.dao.SalaryPaymentRepository;
import com.harish.institutemanagement.dao.SectionRepository;
import com.harish.institutemanagement.dao.SectionTimingRepository;
import com.harish.institutemanagement.dao.StaffRepository;
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
import com.harish.institutemanagement.models.SalaryPayment;
import com.harish.institutemanagement.models.Section;
import com.harish.institutemanagement.models.SectionTiming;
import com.harish.institutemanagement.models.Staff;
import com.harish.institutemanagement.models.Student;
import com.harish.institutemanagement.models.User;
import com.harish.institutemanagement.models.UserPhoneNumber;
import com.harish.institutemanagement.services.EmailService;
import com.harish.institutemanagement.services.SecurityService;

@Controller
@Transactional
public class AdminController {

	@Autowired
	SecurityService securityService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserPhoneNumberRepository userPhoneNumberRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	StaffRepository staffRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	HODRepository hodRepository;

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	FeePaymentRepository feePaymentRepository;

	@Autowired
	SalaryPaymentRepository salaryPaymentRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	SectionTimingRepository sectionTimingRepository;

	@Autowired
	ComplaintRepository complaintRepository;

	@Autowired
	EmailService emailService;

	@GetMapping("/admin/dashboard")
	public String getDashboard() {

		return "admin/dashboard";
	}

	@GetMapping("/admin/profile")
	public String getProfile(Model model) {

		User user = securityService.findLoggedInUser();
		List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberRepository
				.getPhoneNumbersByUsername(user.getUsername());

		model.addAttribute("user", user);
		model.addAttribute("userPhoneNumbers", userPhoneNumbers);

		return "admin/profile";
	}

	@GetMapping("/admin/departments")
	public String getDepartments(Model model) {

		List<Department> departments = departmentRepository.getAll();
		model.addAttribute("departments", departments);

		return "admin/departments";
	}

	@GetMapping("/admin/departments/{departmentId}")
	public String viewDepartment(@PathVariable("departmentId") String departmentId, Model model) {

		Department department = departmentRepository.getDepartment(departmentId);

		if (department == null)
			return "redirect:/admin/departments";

		HOD currentHOD = hodRepository.getCurrentHOD(departmentId);
		List<HOD> hods = hodRepository.getHODsByDepartmentId(departmentId);

		model.addAttribute("department", department);
		model.addAttribute("currentHOD", currentHOD);
		model.addAttribute("hods", hods);
		return "admin/viewDepartment";
	}

	@GetMapping("/admin/departments/add")
	public String addDepartment(Model model) {

		Department department = new Department();
		model.addAttribute("department", department);
		model.addAttribute("submiturl", "/admin/departments/add");
		return "admin/addEditDepartment";
	}

	@PostMapping("/admin/departments/add")
	public String addDepartment(@Valid @ModelAttribute("department") Department department, BindingResult bindingResult,
			Model model) {

		if (departmentRepository.getDepartment(department.getDepartmentId()) != null) {
			bindingResult.rejectValue("departmentId", "Duplicate.department.departmentId");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("submiturl", "/admin/departments/add");
			return "admin/addEditDepartment";
		}

		departmentRepository.createDepartment(department);
		return "redirect:/admin/departments";
	}

	@GetMapping("/admin/departments/{departmentId}/edit")
	public String editDepartment(@PathVariable("departmentId") String departmentId, Model model) {

		Department department = departmentRepository.getDepartment(departmentId);
		model.addAttribute("department", department);
		model.addAttribute("submiturl", "/admin/departments/" + departmentId + "/edit");
		model.addAttribute("edit", true);
		return "admin/addEditDepartment";
	}

	@PostMapping("/admin/departments/{departmentId}/edit")
	public String editDepartment(@PathVariable("departmentId") String departmentId,
			@Valid @ModelAttribute("department") Department department, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("submiturl", "/admin/departments/" + departmentId + "/edit");
			model.addAttribute("edit", true);
			return "admin/addEditDepartment";
		}

		departmentRepository.updateDepartment(department);
		return "redirect:/admin/departments/" + departmentId;
	}

	@GetMapping("/admin/departments/{departmentId}/delete")
	public String deleteDepartment(@PathVariable("departmentId") String departmentId) {
		departmentRepository.deleteDepartment(departmentId);
		return "redirect:/admin/departments";
	}

	@GetMapping("/admin/students")
	public String getStudents(Model model) {

		List<Student> students = studentRepository.getAll();
		model.addAttribute("students", students);

		return "admin/students";
	}

	@GetMapping("/admin/students/{rollNumber}")
	public String viewStudent(@PathVariable("rollNumber") String rollNumber, Model model) {

		Student student = studentRepository.getStudentByRollNumber(rollNumber);

		if (student == null)
			return "redirect:/admin/students";

		User user = userRepository.getUser(student.getUser().getUsername());
		List<UserPhoneNumber> studentPhoneNumbers = userPhoneNumberRepository
				.getPhoneNumbersByUsername(user.getUsername());

		model.addAttribute("student", student);
		model.addAttribute("user", user);
		model.addAttribute("studentPhoneNumbers", studentPhoneNumbers);

		return "admin/viewStudent";
	}

	@GetMapping("/admin/students/add")
	public String addStudent(Model model) {

		List<Department> departments = departmentRepository.getAll();
		Student student = new Student();

		model.addAttribute("student", student);
		model.addAttribute("departments", departments);
		model.addAttribute("submiturl", "/admin/students/add");
		return "admin/addEditStudent";
	}

	@PostMapping("/admin/students/add")
	public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult,
			Model model) {

		List<Department> departments = departmentRepository.getAll();
		User user = student.getUser();

		if (userRepository.getUser(user.getUsername()) != null) {
			bindingResult.rejectValue("user.username", "Duplicate.user.username");
		}

		if (studentRepository.getStudentByRollNumber(student.getRollNumber()) != null) {
			bindingResult.rejectValue("rollNumber", "Duplicate.student.rollNumber");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", departments);
			model.addAttribute("submiturl", "/admin/students/add");
			return "admin/addEditStudent";
		}

		String token = UUID.randomUUID().toString();

		user.setPasswordHash(user.getUsername());
		user.setRole("Student");
		user.setToken(token);

		userRepository.createUser(user);
		student.setUser(user);
		studentRepository.createStudent(student);

		emailService.sendInitialMail(user);

		return "redirect:/admin/students";
	}

	@GetMapping("/admin/students/{rollNumber}/edit")
	public String editStudent(@PathVariable("rollNumber") String rollNumber, Model model) {

		List<Department> departments = departmentRepository.getAll();
		Student student = studentRepository.getStudentByRollNumber(rollNumber);
		User user = userRepository.getUser(student.getUser().getUsername());

		student.setUser(user);

		model.addAttribute("student", student);
		model.addAttribute("departments", departments);
		model.addAttribute("submiturl", "/admin/students/" + student.getRollNumber() + "/edit");
		model.addAttribute("edit", true);
		return "admin/addEditStudent";
	}

	@PostMapping("/admin/students/{rollNumber}/edit")
	public String editStudent(@PathVariable("rollNumber") String rollNumber,
			@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {

		List<Department> departments = departmentRepository.getAll();
		User user = student.getUser();

		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", departments);
			model.addAttribute("submiturl", "/admin/students/" + student.getRollNumber() + "/edit");
			model.addAttribute("edit", true);
			return "admin/addEditStudent";
		}

		String username = studentRepository.getStudentByRollNumber(rollNumber).getUser().getUsername();
		user.setUsername(username);

		userRepository.updateUser(user);
		studentRepository.updateStudent(student);
		return "redirect:/admin/students/" + student.getRollNumber();
	}

	@GetMapping("/admin/students/{username}/delete")
	public String deleteStudent(@PathVariable("username") String username) {
		userRepository.deleteUser(username);
		return "redirect:/admin/students";
	}

	@GetMapping("/admin/professors")
	public String getProfessors(Model model) {

		List<Professor> professors = professorRepository.getAll();
		model.addAttribute("professors", professors);

		return "admin/professors";
	}

	@GetMapping("/admin/professors/{professorId}")
	public String viewProfessor(@PathVariable("professorId") String professorId, Model model) {

		Professor professor = professorRepository.getProfessorByProfessorId(professorId);

		if (professor == null)
			return "redirect:/admin/professors";

		Employee employee = employeeRepository.getEmployeeByEmployeeId(professor.getEmployee().getEmployeeId());
		User user = userRepository.getUser(employee.getUser().getUsername());

		List<UserPhoneNumber> professorPhoneNumbers = userPhoneNumberRepository
				.getPhoneNumbersByUsername(user.getUsername());

		model.addAttribute("professor", professor);
		model.addAttribute("employee", employee);
		model.addAttribute("user", user);
		model.addAttribute("professorPhoneNumbers", professorPhoneNumbers);

		return "admin/viewProfessor";
	}

	@GetMapping("/admin/professors/add")
	public String addProfessor(Model model) {

		List<Department> departments = departmentRepository.getAll();
		Professor professor = new Professor();

		model.addAttribute("professor", professor);
		model.addAttribute("departments", departments);
		model.addAttribute("submiturl", "/admin/professors/add");
		return "admin/addEditProfessor";
	}

	@PostMapping("/admin/professors/add")
	public String addProfessor(@Valid @ModelAttribute("professor") Professor professor, BindingResult bindingResult,
			Model model) {

		List<Department> departments = departmentRepository.getAll();
		Employee employee = professor.getEmployee();
		User user = employee.getUser();

		if (userRepository.getUser(user.getUsername()) != null) {
			bindingResult.rejectValue("employee.user.username", "Duplicate.user.username");
		}

		if (employeeRepository.getEmployeeByEmployeeId(employee.getEmployeeId()) != null) {
			bindingResult.rejectValue("employee.employeeId", "Duplicate.employee.employeeId");
		}

		if (professorRepository.getProfessorByProfessorId(professor.getProfessorId()) != null) {
			bindingResult.rejectValue("professorId", "Duplicate.professor.professorId");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", departments);
			model.addAttribute("submiturl", "/admin/professors/add");
			return "admin/addEditProfessor";
		}

		String token = UUID.randomUUID().toString();

		user.setPasswordHash(user.getUsername());
		user.setRole("Professor");
		user.setToken(token);

		userRepository.createUser(user);
		employeeRepository.createEmployee(employee);
		professorRepository.createProfessor(professor);

		emailService.sendInitialMail(user);

		return "redirect:/admin/professors";

	}

	@GetMapping("/admin/professors/{professorId}/edit")
	public String editProfessor(@PathVariable("professorId") String professorId, Model model) {

		List<Department> departments = departmentRepository.getAll();
		Professor professor = professorRepository.getProfessorByProfessorId(professorId);
		Employee employee = employeeRepository.getEmployeeByEmployeeId(professor.getEmployee().getEmployeeId());
		User user = userRepository.getUser(employee.getUser().getUsername());

		employee.setUser(user);
		professor.setEmployee(employee);

		model.addAttribute("professor", professor);
		model.addAttribute("departments", departments);
		model.addAttribute("submiturl", "/admin/professors/" + professorId + "/edit");
		model.addAttribute("edit", true);
		return "admin/addEditProfessor";
	}

	@PostMapping("/admin/professors/{professorId}/edit")
	public String editProfessor(@PathVariable("professorId") String professorId,
			@Valid @ModelAttribute("professor") Professor professor, BindingResult bindingResult, Model model) {

		List<Department> departments = departmentRepository.getAll();

		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", departments);
			model.addAttribute("submiturl", "/admin/professors/" + professorId + "/edit");
			model.addAttribute("edit", true);
			return "admin/addEditProfessor";
		}

		Employee employee = professor.getEmployee();
		User user = employee.getUser();

		String employeeId = professorRepository.getProfessorByProfessorId(professorId).getEmployee().getEmployeeId();
		String username = employeeRepository.getEmployeeByEmployeeId(employeeId).getUser().getUsername();

		employee.setEmployeeId(employeeId);
		user.setUsername(username);

		userRepository.updateUser(user);
		employeeRepository.updateEmployee(employee);
		professorRepository.updateProfessor(professor);
		return "redirect:/admin/professors/" + professorId;
	}

	@GetMapping("/admin/professors/{username}/delete")
	public String deleteProfessor(@PathVariable("username") String username) {
		userRepository.deleteUser(username);
		return "redirect:/admin/professors";
	}

	@GetMapping("/admin/staff")
	public String getStaff(Model model) {

		List<Staff> staffs = staffRepository.getAll();
		model.addAttribute("staffs", staffs);

		return "admin/staff";
	}

	@GetMapping("/admin/staff/{staffId}")
	public String viewStaff(@PathVariable("staffId") String staffId, Model model) {

		Staff staff = staffRepository.getStaffByStaffId(staffId);

		if (staff == null)
			return "redirect:/admin/staff";

		Employee employee = employeeRepository.getEmployeeByEmployeeId(staff.getEmployee().getEmployeeId());
		User user = userRepository.getUser(employee.getUser().getUsername());

		List<UserPhoneNumber> staffPhoneNumbers = userPhoneNumberRepository
				.getPhoneNumbersByUsername(user.getUsername());

		model.addAttribute("staff", staff);
		model.addAttribute("employee", employee);
		model.addAttribute("user", user);
		model.addAttribute("staffPhoneNumbers", staffPhoneNumbers);

		return "admin/viewStaff";
	}

	@GetMapping("/admin/staff/add")
	public String addStaff(Model model) {

		Staff staff = new Staff();

		model.addAttribute("staff", staff);
		model.addAttribute("submiturl", "/admin/staff/add");
		return "admin/addEditStaff";
	}

	@PostMapping("/admin/staff/add")
	public String addStaff(@Valid @ModelAttribute("staff") Staff staff, BindingResult bindingResult, Model model) {

		Employee employee = staff.getEmployee();
		User user = employee.getUser();

		if (userRepository.getUser(user.getUsername()) != null) {
			bindingResult.rejectValue("employee.user.username", "Duplicate.user.username");
		}

		if (employeeRepository.getEmployeeByEmployeeId(employee.getEmployeeId()) != null) {
			bindingResult.reject("employee.employeeId", "Duplicate.employee.employeeId");
		}

		if (staffRepository.getStaffByStaffId(staff.getStaffId()) != null) {
			bindingResult.reject("staffId", "Duplicate.staff.staffId");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("submiturl", "/admin/staff/add");
			return "admin/addEditStaff";
		}

		String token = UUID.randomUUID().toString();

		user.setPasswordHash(user.getUsername());
		user.setRole("Staff");
		user.setToken(token);

		userRepository.createUser(user);
		employeeRepository.createEmployee(employee);
		staffRepository.createStaff(staff);

		emailService.sendInitialMail(user);

		return "redirect:/admin/staff";
	}

	@GetMapping("/admin/staff/{staffId}/edit")
	public String editStaff(@PathVariable("staffId") String staffId, Model model) {

		Staff staff = staffRepository.getStaffByStaffId(staffId);
		Employee employee = employeeRepository.getEmployeeByEmployeeId(staff.getEmployee().getEmployeeId());
		User user = userRepository.getUser(employee.getUser().getUsername());

		employee.setUser(user);
		staff.setEmployee(employee);

		model.addAttribute("staff", staff);
		model.addAttribute("submiturl", "/admin/staff/" + staffId + "/edit");
		model.addAttribute("edit", true);
		return "admin/addEditStaff";
	}

	@PostMapping("/admin/staff/{staffId}/edit")
	public String editStaff(@PathVariable("staffId") String staffId, @Valid @ModelAttribute("staff") Staff staff,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("submiturl", "/admin/staff/" + staffId + "/edit");
			model.addAttribute("edit", true);
			return "admin/addEditStaff";
		}

		Employee employee = staff.getEmployee();
		User user = employee.getUser();

		String employeeId = staffRepository.getStaffByStaffId(staffId).getEmployee().getEmployeeId();
		String username = employeeRepository.getEmployeeByEmployeeId(employeeId).getUser().getUsername();

		employee.setEmployeeId(employeeId);
		user.setUsername(username);

		userRepository.updateUser(user);
		employeeRepository.updateEmployee(employee);
		staffRepository.updateStaff(staff);
		return "redirect:/admin/staff/" + staffId;
	}

	@GetMapping("/admin/staff/{username}/delete")
	public String deleteStaff(@PathVariable("username") String username) {
		userRepository.deleteUser(username);
		return "redirect:/admin/staff";
	}

	@GetMapping("/admin/courses")
	public String getCourses(Model model) {

		List<Course> courses = courseRepository.getAll();
		model.addAttribute("courses", courses);

		return "admin/courses";
	}

	@GetMapping("/admin/courses/{courseId}")
	public String viewCourse(@PathVariable("courseId") String courseId, Model model) {

		Course course = courseRepository.getCourseByCourseId(courseId);

		if (courseId == null)
			return "redirect:/admin/courses";

		model.addAttribute("course", course);
		return "admin/viewCourse";
	}

	@GetMapping("/admin/courses/add")
	public String addCourse(Model model) {

		List<Department> departments = departmentRepository.getAll();
		Course course = new Course();
		model.addAttribute("course", course);
		model.addAttribute("departments", departments);
		model.addAttribute("submiturl", "/admin/courses/add");
		return "admin/addEditCourse";
	}

	@PostMapping("/admin/courses/add")
	public String addCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {

		List<Department> departments = departmentRepository.getAll();

		if (courseRepository.getCourseByCourseId(course.getCourseId()) != null) {
			bindingResult.rejectValue("courseId", "Duplicate.course.courseId");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", departments);
			model.addAttribute("submit", "/admin/courses/add");
			return "admin/addEditCourse";
		}

		courseRepository.createCourse(course);
		return "redirect:/admin/courses";
	}

	@GetMapping("/admin/courses/{courseId}/edit")
	public String editCourse(@PathVariable("courseId") String courseId, Model model) {

		Course course = courseRepository.getCourseByCourseId(courseId);
		List<Department> departments = departmentRepository.getAll();
		model.addAttribute("course", course);
		model.addAttribute("departments", departments);
		model.addAttribute("submiturl", "/admin/courses/" + courseId + "/edit");
		model.addAttribute("edit", true);
		return "admin/addEditCourse";
	}

	@PostMapping("/admin/courses/{courseId}/edit")
	public String editCourse(@PathVariable("courseId") String courseId, @Valid @ModelAttribute("course") Course course,
			BindingResult bindingResult, Model model) {

		List<Department> departments = departmentRepository.getAll();

		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", departments);
			model.addAttribute("submit", "/admin/courses/" + courseId + "/edit");
			model.addAttribute("edit", true);
			return "admin/addEditCourse";
		}

		courseRepository.updateCourse(course);
		return "redirect:/admin/courses/" + courseId;
	}

	@GetMapping("/admin/courses/{courseId}/delete")
	public String deleteCourse(@PathVariable("courseId") String courseId) {
		courseRepository.deleteCourse(courseId);
		return "redirect:/admin/courses";
	}

	@GetMapping("/admin/sections")
	public String getSections(Model model) {

		List<Section> sections = sectionRepository.getAll();
		model.addAttribute("sections", sections);

		return "admin/sections";
	}

	@GetMapping("/admin/sections/{courseId}/{sectionId}")
	public String viewSection(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			Model model) {

		Section section = sectionRepository.getSection(sectionId, courseId);
		Course course = courseRepository.getCourseByCourseId(courseId);
		List<Enrollment> enrollments = enrollmentRepository.getEnrollmentsBySection(sectionId, courseId);
		Enrollment enrollment = new Enrollment();
		Professor professor = professorRepository.getProfessorByProfessorId(section.getProfessor().getProfessorId());
		Employee employee = employeeRepository.getEmployeeByEmployeeId(professor.getEmployee().getEmployeeId());
		User user = userRepository.getUser(employee.getUser().getUsername());
		List<SectionTiming> sectionTimings = sectionTimingRepository.getSectionTimings(sectionId, courseId);

		employee.setUser(user);
		professor.setEmployee(employee);
		section.setProfessor(professor);
		section.setCourse(course);

		model.addAttribute("section", section);
		model.addAttribute("sectionTimings", sectionTimings);
		model.addAttribute("enrollments", enrollments);
		model.addAttribute("enrollment", enrollment);
		model.addAttribute("submiturl", "/admin/enrollments/" + courseId + "/" + sectionId + "/add");
		return "/admin/viewSection";
	}

	@GetMapping("/admin/sections/add")
	public String addSection(Model model) {

		Section section = new Section();
		model.addAttribute("section", section);
		model.addAttribute("submiturl", "/admin/sections/add");
		return "admin/addEditSection";
	}

	@PostMapping("/admin/sections/add")
	public String addSection(@Valid @ModelAttribute("section") Section section, BindingResult bindingResult,
			Model model) {

		if (courseRepository.getCourseByCourseId(section.getCourse().getCourseId()) == null) {
			bindingResult.rejectValue("course.courseId", "Invalid.course.courseId");
		}

		if (sectionRepository.getSection(section.getSectionId(), section.getCourse().getCourseId()) != null) {
			bindingResult.rejectValue("sectionId", "Duplicate.section.sectionId");
		}

		if (professorRepository.getProfessorByProfessorId(section.getProfessor().getProfessorId()) == null) {
			bindingResult.rejectValue("professor.professorId", "Invalid.professor.professorId");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("submiturl", "/admin/sections/add");
			return "admin/addEditSection";
		}

		sectionRepository.createSection(section);
		return "redirect:/admin/sections";
	}

	@GetMapping("/admin/sections/{courseId}/{sectionId}/edit")
	public String editSection(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			Model model) {
		Section section = sectionRepository.getSection(sectionId, courseId);
		model.addAttribute("section", section);
		model.addAttribute("submiturl", "/admin/sections/" + courseId + "/" + sectionId + "/edit");
		model.addAttribute("edit", true);
		return "admin/addEditSection";
	}

	@PostMapping("/admin/sections/{courseId}/{sectionId}/edit")
	public String editSection(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			@Valid @ModelAttribute("section") Section section, BindingResult bindingResult, Model model) {

		Course course = new Course();
		course.setCourseId(courseId);
		section.setCourse(course);

		if (professorRepository.getProfessorByProfessorId(section.getProfessor().getProfessorId()) == null) {
			bindingResult.rejectValue("professor.professorId", "Invalid.professor.professorId");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("section", section);
			model.addAttribute("submiturl", "/admin/sections/" + courseId + "/" + sectionId + "/edit");
			model.addAttribute("edit", true);
			return "admin/addEditSection";
		}

		sectionRepository.updateSection(section);
		return "redirect:/admin/sections/" + courseId + "/" + sectionId;
	}

	@GetMapping("/admin/sections/{courseId}/{sectionId}/delete")
	public String deleteStudent(@PathVariable("courseId") String courseId,
			@PathVariable("sectionId") String sectionId) {
		sectionRepository.deleteSection(sectionId, courseId);
		return "redirect:/admin/sections";
	}

	@GetMapping("/admin/fee-transactions")
	public String getFeeTransactions(Model model) {

		List<FeePayment> feePayments = feePaymentRepository.getAll();
		model.addAttribute("feePayments", feePayments);

		return "admin/feeTransactions";
	}

	@GetMapping("/admin/fee-transactions/add")
	public String addFeeTranasction(Model model) {

		FeePayment feePayment = new FeePayment();
		model.addAttribute("feePayment", feePayment);
		model.addAttribute("submiturl", "/admin/fee-transactions/add");
		return "admin/addEditFeeTransaction";
	}

	@PostMapping("/admin/fee-transactions/add")
	public String addFeeTransaction(@Valid @ModelAttribute("feePayment") FeePayment feePayment,
			BindingResult bindingResult, Model model) {

		if (feePaymentRepository.getFeePayment(feePayment.getTransactionId()) != null) {
			bindingResult.rejectValue("transactionId", "Duplicate.feePayment.transactionId");
		}

		if (studentRepository.getStudentByRollNumber(feePayment.getStudent().getRollNumber()) == null) {
			bindingResult.rejectValue("student.rollNumber", "Invalid.student.rollNumber");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("submiturl", "/admin/fee-transactions/add");
			return "admin/addEditFeeTransaction";
		}

		feePaymentRepository.createFeePayment(feePayment);
		return "redirect:/admin/fee-transactions";

	}

	@GetMapping("/admin/fee-transactions/{transactionId}/edit")
	public String editFeeTranasction(@PathVariable("transactionId") String transactionId, Model model) {

		FeePayment feePayment = feePaymentRepository.getFeePayment(transactionId);
		if (feePayment == null)
			return "redirect:/admin/fee-transactions";
		model.addAttribute("feePayment", feePayment);
		model.addAttribute("submiturl", "/admin/fee-transactions/" + transactionId + "/edit");
		model.addAttribute("edit", true);
		return "admin/addEditFeeTransaction";
	}

	@PostMapping("/admin/fee-transactions/{transactionId}/edit")
	public String editFeeTransaction(@PathVariable("transactionId") String transactionId,
			@Valid @ModelAttribute("feePayment") FeePayment feePayment, BindingResult bindingResult, Model model) {

		if (studentRepository.getStudentByRollNumber(feePayment.getStudent().getRollNumber()) == null) {
			bindingResult.rejectValue("student.rollNumber", "Invalid.student.rollNumber");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("submiturl", "/admin/fee-transactions/" + transactionId + "/edit");
			model.addAttribute("edit", true);
			return "admin/addEditFeeTransaction";
		}

		feePaymentRepository.updateFeePayment(feePayment);
		return "redirect:/admin/fee-transactions";

	}

	@GetMapping("/admin/fee-transactions/{transactionId}/delete")
	public String deleteFeeTransaction(@PathVariable("transactionId") String transactionId) {
		feePaymentRepository.deleteFeePayment(transactionId);
		return "redirect:/admin/fee-transactions";
	}

	@GetMapping("/admin/salary-transactions")
	public String getSalaryTransactions(Model model) {

		List<SalaryPayment> salaryPayments = salaryPaymentRepository.getAll();
		model.addAttribute("salaryPayments", salaryPayments);

		return "admin/salaryTransactions";
	}

	@GetMapping("/admin/salary-transactions/add")
	public String addSalaryTransaction(Model model) {

		SalaryPayment salaryPayment = new SalaryPayment();
		model.addAttribute("salaryPayment", salaryPayment);
		model.addAttribute("submiturl", "/admin/salary-transactions/add");
		return "admin/addEditSalaryTransaction";
	}

	@PostMapping("/admin/salary-transactions/add")
	public String addSalaryTransaction(@Valid @ModelAttribute("salaryPayment") SalaryPayment salaryPayment,
			BindingResult bindingResult, Model model) {

		if (salaryPaymentRepository.getSalaryPayment(salaryPayment.getTransactionId()) != null) {
			bindingResult.rejectValue("transactionId", "Duplicate.salaryPayment.transactionId");
		}

		if (employeeRepository.getEmployeeByEmployeeId(salaryPayment.getEmployee().getEmployeeId()) == null) {
			bindingResult.rejectValue("employee.employeeId", "Invalid.employee.employeeId");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("submiturl", "/admin/salary-transactions/add");
			return "admin/addEditSalaryTransaction";
		}

		salaryPaymentRepository.createSalaryPayment(salaryPayment);
		return "redirect:/admin/salary-transactions";
	}

	@GetMapping("/admin/salary-transactions/{transactionId}/edit")
	public String editSalaryTransaction(@PathVariable("transactionId") String transactionId, Model model) {

		SalaryPayment salaryPayment = salaryPaymentRepository.getSalaryPayment(transactionId);
		if (salaryPayment == null)
			return "redirect:/admin/salary-transactions";
		model.addAttribute("salaryPayment", salaryPayment);
		model.addAttribute("submiturl", "/admin/salary-transactions/" + transactionId + "/edit");
		model.addAttribute("edit", true);
		return "admin/addEditSalaryTransaction";
	}

	@PostMapping("/admin/salary-transactions/{transactionId}/edit")
	public String editSalaryTransaction(@PathVariable("transactionId") String transactionId,
			@Valid @ModelAttribute("salaryPayment") SalaryPayment salaryPayment, BindingResult bindingResult,
			Model model) {

		if (employeeRepository.getEmployeeByEmployeeId(salaryPayment.getEmployee().getEmployeeId()) == null) {
			bindingResult.rejectValue("employee.employeeId", "Invalid.employee.employeeId");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("submiturl", "/admin/salary-transactions/" + transactionId + "/edit");
			model.addAttribute("edit", true);
			return "admin/addEditSalaryTransaction";
		}

		salaryPaymentRepository.updateSalaryPayment(salaryPayment);
		return "redirect:/admin/salary-transactions";
	}

	@GetMapping("/admin/salary-transactions/{transactionId}/delete")
	public String deleteSalaryTransaction(@PathVariable("transactionId") String transactionId) {
		salaryPaymentRepository.deleteSalaryPayment(transactionId);
		return "redirect:/admin/salary-transactions";
	}

	@PostMapping("/admin/enrollments/{courseId}/{sectionId}/add")
	public String addEnrollment(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			@Valid @ModelAttribute("enrollment") Enrollment enrollment, BindingResult bindingResult, Model model) {
		if (studentRepository.getStudentByRollNumber(enrollment.getStudent().getRollNumber()) == null) {
			bindingResult.rejectValue("student.rollNumber", "Invalid.student.rollNumber");
		}

		if (enrollmentRepository.getEnrollment(courseId, sectionId, enrollment.getStudent().getRollNumber()) != null) {
			bindingResult.rejectValue("student.rollNumber", "Duplicate.student.rollNumber");
		}

		if (bindingResult.hasErrors()) {

			Section section = sectionRepository.getSection(sectionId, courseId);
			Course course = courseRepository.getCourseByCourseId(courseId);
			List<Enrollment> enrollments = enrollmentRepository.getEnrollmentsBySection(sectionId, courseId);
			Professor professor = professorRepository
					.getProfessorByProfessorId(section.getProfessor().getProfessorId());
			Employee employee = employeeRepository.getEmployeeByEmployeeId(professor.getEmployee().getEmployeeId());
			User user = userRepository.getUser(employee.getUser().getUsername());
			List<SectionTiming> sectionTimings = sectionTimingRepository.getSectionTimings(sectionId, courseId);

			employee.setUser(user);
			professor.setEmployee(employee);
			section.setProfessor(professor);
			section.setCourse(course);

			model.addAttribute("section", section);
			model.addAttribute("sectionTimings", sectionTimings);
			model.addAttribute("enrollments", enrollments);
			model.addAttribute("submiturl", "/admin/enrollments/" + courseId + "/" + sectionId + "/add");
			return "admin/viewSection";
		}

		enrollmentRepository.createEnrollment(courseId, sectionId, enrollment.getStudent().getRollNumber());
		return "redirect:/admin/sections/" + courseId + "/" + sectionId;
	}

	@GetMapping("/admin/enrollments/{courseId}/{sectionId}/{rollNumber}/edit")
	public String editEnrollment(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			@PathVariable("rollNumber") String rollNumber, Model model) {

		Enrollment enrollment = enrollmentRepository.getEnrollment(courseId, sectionId, rollNumber);

		model.addAttribute("courseId", courseId);
		model.addAttribute("sectionId", sectionId);
		model.addAttribute("rollNumber", rollNumber);
		model.addAttribute("enrollment", enrollment);
		model.addAttribute("submiturl",
				"/admin/enrollments/" + courseId + "/" + sectionId + "/" + rollNumber + "/edit");
		return "admin/addEditEnrollment";
	}

	@PostMapping("/admin/enrollments/{courseId}/{sectionId}/{rollNumber}/edit")
	public String editEnrollment(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			@PathVariable("rollNumber") String rollNumber, @ModelAttribute("enrollment") Enrollment enrollment,
			Model model) {
		enrollmentRepository.updateEnrollment(enrollment, courseId, sectionId, rollNumber);
		return "redirect:/admin/sections/" + courseId + "/" + sectionId;
	}

	@GetMapping("/admin/enrollments/{courseId}/{sectionId}/{rollNumber}/delete")
	public String deleteEnrollment(@PathVariable("courseId") String courseId,
			@PathVariable("sectionId") String sectionId, @PathVariable("rollNumber") String rollNumber) {
		enrollmentRepository.deleteEnrollment(courseId, sectionId, rollNumber);
		return "redirect:/admin/sections/" + courseId + "/" + sectionId;
	}

	@GetMapping("/admin/hods/{departmentId}/add")
	public String addHod(@PathVariable("departmentId") String departmentId, Model model) {

		HOD hod = new HOD();
		List<Professor> professors = professorRepository.getProfessorByDepartment(departmentId);
		model.addAttribute("hod", hod);
		model.addAttribute("departmentId", departmentId);
		model.addAttribute("professors", professors);
		model.addAttribute("submiturl", "/admin/hods/" + departmentId + "/add");
		return "admin/addEditHod";
	}

	@PostMapping("/admin/hods/{departmentId}/add")
	public String addHod(@PathVariable("departmentId") String departmentId, @Valid @ModelAttribute("hod") HOD hod,
			BindingResult bindingResult, Model model) {

		HOD currentHod = hodRepository.getCurrentHOD(departmentId);
		List<Professor> professors = professorRepository.getProfessorByDepartment(departmentId);
		if (currentHod != null && hod.getEndDate() == null) {
			bindingResult.rejectValue("endDate", "Duplicate.hod.endDate");
		}

		if (hodRepository.getHOD(departmentId, hod.getProfessor().getProfessorId(), hod.getStartDate()) != null) {
			bindingResult.rejectValue("startDate", "Duplicate.hod.startDate");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("departmentId", departmentId);
			model.addAttribute("professors", professors);
			model.addAttribute("submiturl", "/admin/hods/" + departmentId + "/add");
			return "admin/addEditHod";
		}

		hodRepository.createHOD(hod);
		return "redirect:/admin/departments/" + departmentId;
	}

	@GetMapping("/admin/hods/{departmentId}/{professorId}/{startDate}/edit")
	public String editHod(@PathVariable("departmentId") String departmentId,
			@PathVariable("professorId") String professorId, @PathVariable("startDate") Date startDate, Model model) {

		HOD hod = hodRepository.getHOD(departmentId, professorId, startDate);

		model.addAttribute("hod", hod);
		model.addAttribute("departmentId", departmentId);
		model.addAttribute("submiturl", "/admin/hods/" + departmentId + "/" + professorId + "/" + startDate + "/edit");
		model.addAttribute("edit", true);
		return "admin/addEditHod";
	}

	@PostMapping("/admin/hods/{departmentId}/{professorId}/{startDate}/edit")
	public String editHod(@PathVariable("departmentId") String departmentId,
			@PathVariable("professorId") String professorId, @PathVariable("startDate") Date startDate,
			@Valid @ModelAttribute("hod") HOD hod, BindingResult bindingResult, Model model) {

		HOD currentHod = hodRepository.getCurrentHOD(departmentId);
		Professor professor = new Professor();
		professor.setProfessorId(professorId);
		hod.setProfessor(professor);

		if (currentHod != null && hod.getEndDate() == null) {
			bindingResult.rejectValue("endDate", "Duplicate.hod.endDate");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("hod", hod);
			model.addAttribute("departmentId", departmentId);
			model.addAttribute("submiturl",
					"/admin/hods/" + departmentId + "/" + professorId + "/" + startDate + "/edit");
			model.addAttribute("edit", true);
			return "admin/addEditHod";
		}

		hodRepository.updateHOD(hod);
		return "redirect:/admin/departments/" + departmentId;
	}

	@GetMapping("/admin/hods/{departmentId}/{professorId}/{startDate}/delete")
	public String deleteHod(@PathVariable("departmentId") String departmentId,
			@PathVariable("professorId") String professorId, @PathVariable("startDate") Date startDate) {
		hodRepository.deleteHOD(departmentId, professorId, startDate);
		return "redirect:/admin/departments/" + departmentId;
	}

	@GetMapping("/admin/user/{username}/phone")
	public String managePhone(@PathVariable("username") String username, Model model) {

		User user = userRepository.getUser(username);
		List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberRepository.getPhoneNumbersByUsername(username);
		UserPhoneNumber phoneNumber = new UserPhoneNumber();
		String finishurl = "/admin/" + user.getRole().toLowerCase();
		if (!user.getRole().equals("Staff")) {
			finishurl += 's';
		}

		model.addAttribute("userPhoneNumbers", userPhoneNumbers);
		model.addAttribute("phoneNumber", phoneNumber);
		model.addAttribute("submiturl", "/admin/user/" + username + "/phone");
		model.addAttribute("finishurl", finishurl);

		return "admin/addEditPhone";
	}

	@PostMapping("/admin/user/{username}/phone")
	public String managePhone(@PathVariable("username") String username,
			@Valid @ModelAttribute("phoneNumber") UserPhoneNumber phoneNumber, BindingResult bindingResult,
			Model model) {

		User user = userRepository.getUser(username);
		List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberRepository.getPhoneNumbersByUsername(username);

		if (userPhoneNumberRepository.getPhoneNumber(username, phoneNumber.getPhoneNumber()) != null) {
			bindingResult.rejectValue("phoneNumber", "Duplicate.user.phoneNumber");
		}

		String finishurl = "/admin/" + user.getRole().toLowerCase();
		if (!user.getRole().equals("Staff")) {
			finishurl += 's';
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("userPhoneNumbers", userPhoneNumbers);
			model.addAttribute("submiturl", "/admin/user/" + username + "/phone");
			model.addAttribute("finishurl", finishurl);
			return "admin/addEditPhone";
		}

		userPhoneNumberRepository.createPhoneNumber(phoneNumber);
		return "redirect:/admin/user/" + username + "/phone";
	}

	@GetMapping("/admin/user/{username}/{phoneNumber}/delete")
	public String deletePhone(@PathVariable("username") String username,
			@PathVariable("phoneNumber") String phoneNumber) {
		userPhoneNumberRepository.deletePhoneNumber(username, phoneNumber);
		return "redirect:/admin/user/" + username + "/phone";
	}

	@GetMapping("/admin/sections/{courseId}/{sectionId}/timings")
	public String manageSectionTimings(@PathVariable("courseId") String courseId,
			@PathVariable("sectionId") String sectionId, Model model) {

		List<SectionTiming> sectionTimings = sectionTimingRepository.getSectionTimings(sectionId, courseId);

		SectionTiming sectionTiming = new SectionTiming();

		model.addAttribute("sectionTimings", sectionTimings);
		model.addAttribute("sectionTiming", sectionTiming);
		model.addAttribute("submiturl", "/admin/sections/" + courseId + "/" + sectionId + "/timings");
		model.addAttribute("finishurl", "/admin/sections/" + courseId + "/" + sectionId);
		return "admin/addEditTiming";
	}

	@PostMapping("/admin/sections/{courseId}/{sectionId}/timings")
	public String manageSectionTimings(@PathVariable("courseId") String courseId,
			@PathVariable("sectionId") String sectionId,
			@Valid @ModelAttribute("sectionTiming") SectionTiming sectionTiming, BindingResult bindingResult,
			Model model) {

		List<SectionTiming> sectionTimings = sectionTimingRepository.getSectionTimings(sectionId, courseId);
		sectionTiming.setSectionId(sectionId);
		sectionTiming.setCourseId(courseId);

		if (sectionTimingRepository.getSectionTiming(sectionTiming) != null) {
			bindingResult.rejectValue("slot", "Duplicate.sectionTiming.slot");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("sectionTimings", sectionTimings);
			model.addAttribute("submiturl", "/admin/sections/" + courseId + "/" + sectionId + "/timings");
			model.addAttribute("finishurl", "/admin/sections/" + courseId + "/" + sectionId);
			return "admin/addEditTiming";
		}

		sectionTimingRepository.addSectionTiming(sectionTiming);
		return "redirect:/admin/sections/" + courseId + "/" + sectionId + "/timings";
	}

	@GetMapping("/admin/sections/{courseId}/{sectionId}/{day}/{slot}/delete")
	public String deleteTiming(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId,
			@PathVariable("day") String day, @PathVariable("slot") String slot) {
		SectionTiming sectionTiming = new SectionTiming();
		sectionTiming.setCourseId(courseId);
		sectionTiming.setSectionId(sectionId);
		sectionTiming.setDay(day);
		sectionTiming.setSlot(slot);
		sectionTimingRepository.deleteSectionTiming(sectionTiming);
		return "redirect:/admin/sections/" + courseId + "/" + sectionId + "/timings";
	}

	@GetMapping("/admin/complaints")
	public String getComplaints(Model model) {

		List<Complaint> complaints = complaintRepository.getAll();
		model.addAttribute("complaints", complaints);

		return "admin/complaints";
	}

	@GetMapping("/admin/complaints/{complaintId}/respond")
	public String respondComplaint(@PathVariable("complaintId") String complaintId, Model model) {

		Complaint complaint = complaintRepository.getComplaint(complaintId);
		if (complaint == null)
			return "redirect:/admin/complaints";

		model.addAttribute("complaint", complaint);
		model.addAttribute("submiturl", "/admin/complaints/" + complaintId + "/respond");
		return "admin/respondComplaint";
	}

	@PostMapping("/admin/complaints/{complaintId}/respond")
	public String respondComplaint(@PathVariable("complaintId") String complaintId,
			@ModelAttribute("complaint") Complaint complaint) {

		Complaint oldComplaint = complaintRepository.getComplaint(complaintId);
		complaint.setComplaintId(oldComplaint.getComplaintId());
		complaintRepository.updateComplaint(complaint);
		return "redirect:/admin/complaints";
	}
}
