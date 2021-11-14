package com.harish.institutemanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.harish.institutemanagement.dao.EmployeeRepository;
import com.harish.institutemanagement.dao.SalaryPaymentRepository;
import com.harish.institutemanagement.dao.StaffRepository;
import com.harish.institutemanagement.dao.UserPhoneNumberRepository;
import com.harish.institutemanagement.models.Employee;
import com.harish.institutemanagement.models.SalaryPayment;
import com.harish.institutemanagement.models.Staff;
import com.harish.institutemanagement.models.User;
import com.harish.institutemanagement.models.UserPhoneNumber;
import com.harish.institutemanagement.services.SecurityService;

@Controller
@Transactional
public class StaffController {

	@Autowired
	SecurityService securityService;

	@Autowired
	UserPhoneNumberRepository userPhoneNumberRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	StaffRepository staffRepository;

	@Autowired
	SalaryPaymentRepository salaryPaymentRepository;

	@GetMapping("/staff/dashboard")
	public String getDashboard() {

		return "staff/dashboard";
	}

	@GetMapping("/staff/profile")
	public String getProfile(Model model) {

		User user = securityService.findLoggedInUser();
		List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberRepository
				.getPhoneNumbersByUsername(user.getUsername());
		Employee employee = employeeRepository.getEmployeeByUsername(user.getUsername());

		if (employee == null)
			return "redirect:/staff/dashboard";

		Staff staff = staffRepository.getStaffByEmployeeId(employee.getEmployeeId());

		model.addAttribute("user", user);
		model.addAttribute("userPhoneNumbers", userPhoneNumbers);
		model.addAttribute("employee", employee);
		model.addAttribute("staff", staff);

		return "staff/profile";
	}

	@GetMapping("/staff/staff")
	public String getStaff(Model model) {

		List<Staff> staffs = staffRepository.getAll();
		model.addAttribute("staffs", staffs);
		return "staff/staff";
	}

	@GetMapping("/staff/salary-transactions")
	public String getSalaryTransactions(Model model) {

		User user = securityService.findLoggedInUser();
		Employee employee = employeeRepository.getEmployeeByUsername(user.getUsername());
		List<SalaryPayment> salaryPayments = salaryPaymentRepository
				.getSalaryPaymentsByEmployeeId(employee.getEmployeeId());

		model.addAttribute("salaryPayments", salaryPayments);
		return "staff/salaryTransactions";
	}
}
