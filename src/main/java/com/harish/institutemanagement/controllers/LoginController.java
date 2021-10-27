package com.harish.institutemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.harish.institutemanagement.models.User;
import com.harish.institutemanagement.services.SecurityService;

@Controller
@Transactional
public class LoginController {

	@Autowired
	private SecurityService securityService;

	@GetMapping("/login")
	public String login(Model model, String error, String logout, String emailSent) {

		if (error != null)
			model.addAttribute("error", "Invalid username or password");

		if (logout != null)
			model.addAttribute("success", "You have been logged out successfully !");

		if (emailSent != null)
			model.addAttribute("success", "Your password reset mail has been sent");

		return "login";
	}

	@GetMapping("/welcome")
	public String user() {

		User user = securityService.findLoggedInUser();

		System.out.println(user);

		if (user == null)
			return "redirect:/login?error";

		if (user.getRole().equals("Admin"))
			return "redirect:/admin";

		else if (user.getRole().equals("Student"))
			return "redirect:/student";

		else if (user.getRole().equals("Professor"))
			return "redirect:/professor";

		else if (user.getRole().equals("Staff"))
			return "redirect:/staff";

		else
			return "redirect:/login?error";

	}

}
