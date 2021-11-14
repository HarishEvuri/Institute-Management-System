package com.harish.institutemanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Transactional
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "template/home";
	}
}
