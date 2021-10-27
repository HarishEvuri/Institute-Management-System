package com.harish.institutemanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {

	@GetMapping("/professor")
	public String getProfessor() {
		return "Hello Professor";
	}
}
