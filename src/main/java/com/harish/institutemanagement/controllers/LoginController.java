package com.harish.institutemanagement.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harish.institutemanagement.dao.UserRepository;
import com.harish.institutemanagement.models.User;
import com.harish.institutemanagement.services.EmailService;
import com.harish.institutemanagement.services.SecurityService;

@Controller
@Transactional
public class LoginController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/user/login")
	public String login(Model model, String error, String logout, String emailSent, String resetPassword) {

		if (error != null)
			model.addAttribute("error", "Invalid username or password");

		if (logout != null)
			model.addAttribute("success", "You have been logged out successfully !");

		if (emailSent != null)
			model.addAttribute("success", "Your password reset mail has been sent");

		if (resetPassword != null)
			model.addAttribute("success", "Password reset successful !");

		return "template/login";
	}

	@GetMapping("/welcome")
	public String user() {

		User user = securityService.findLoggedInUser();

		if (user == null)
			return "redirect:/user/login?error";

		if (user.getRole().equals("Admin"))
			return "redirect:/admin/dashboard";

		else if (user.getRole().equals("Student"))
			return "redirect:/student/dashboard";

		else if (user.getRole().equals("Professor"))
			return "redirect:/professor/dashboard";

		else if (user.getRole().equals("Staff"))
			return "redirect:/staff/dashboard";

		else
			return "redirect:/user/login?error";

	}

	@GetMapping("/user/forgot-password")
	public String forgotPassword(Model model) {
		model.addAttribute("username", "");
		model.addAttribute("submiturl", "/user/forgot-password");
		return "template/forgotPassword";
	}

	@PostMapping("/user/forgot-password")
	public String forgotPassword(@RequestParam("username") String username, Model model) {

		User user = userRepository.getUser(username);

		if (user == null) {
			model.addAttribute("error", "The username does not exist");
			model.addAttribute("submiturl", "/user/forgot-password");
			return "template/forgotPassword";
		}

		String token = UUID.randomUUID().toString();
		user.setToken(token);
		userRepository.updateToken(user);

		emailService.sendForgotMail(user);
		return "redirect:/user/login?emailSent";
	}

	@GetMapping("/user/reset-password")
	public String resetPassword(String token, Model model) {

		User user = userRepository.getUserByToken(token);
		if (user == null)
			return "redirect:/";

		model.addAttribute("submiturl", "/user/reset-password?token=" + token);
		return "template/resetPassword";

	}

	@PostMapping("/user/reset-password")
	public String resetPassword(String token, @RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword, Model model) {

		User user = userRepository.getUserByToken(token);
		if (user == null)
			return "redirect:/";

		if (!password.equals(confirmPassword)) {
			model.addAttribute("error", "The passwords do not match");
			model.addAttribute("submiturl", "/user/reset-password?token=" + token);
			return "template/resetPassword";
		}

		userRepository.changePassword(user.getUsername(), password);
		return "redirect:/user/login?resetPassword";
	}

	@GetMapping("/user/change-password")
	public String changePassword(Model model) {

		User user = securityService.findLoggedInUser();
		if (user == null)
			return "redirect:/";

		model.addAttribute("submiturl", "/user/change-password");
		return "template/changePassword";
	}

	@PostMapping("/user/change-password")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
			Model model) {

		User user = securityService.findLoggedInUser();
		if (user == null)
			return "redirect:/";

		if (!bCryptPasswordEncoder.matches(oldPassword, user.getPasswordHash())) {
			model.addAttribute("error", "Invalid Password");
			model.addAttribute("submiturl", "/user/change-password");
			return "template/changePassword";
		}
		if (!newPassword.equals(confirmPassword)) {
			model.addAttribute("error", "The passwords do not match");
			model.addAttribute("submiturl", "/user/change-password");
			return "template/changePassword";
		}

		userRepository.changePassword(user.getUsername(), newPassword);
		securityService.autoLogout();
		return "redirect:/user/login?resetPassword";
	}
}
