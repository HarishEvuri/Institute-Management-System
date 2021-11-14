package com.harish.institutemanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harish.institutemanagement.models.User;

@Transactional
@Service("emailSendService")
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendInitialMail(User user) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmailAddress());
		message.setSubject("Registration Successful!");

		String hostname = System.getenv("SERVER_HOSTNAME");
		if (hostname == null)
			hostname = "http://localhost:8080";

		message.setText("Your account has been registered on SIT as " + user.getRole() + " with username : "
				+ user.getUsername() + " and temporary password : " + user.getUsername()
				+ "\n To reset your password, please click here : " + hostname + "/user/reset-password?token="
				+ user.getToken());

		javaMailSender.send(message);
	}

	public void sendForgotMail(User user) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmailAddress());
		message.setSubject("Reset Your Password!");

		String hostname = System.getenv("SERVER_HOSTNAME");
		if (hostname == null)
			hostname = "http://localhost:8080";

		message.setText("To Reset Your password\nPlease click here : " + hostname + "/user/reset-password?token="
				+ user.getToken());

		javaMailSender.send(message);
	}
}
