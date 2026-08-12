package com.OyoBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OyoBooking.entity.Register;
import com.OyoBooking.service.RegisterService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class RegisterController {
	@Autowired
	RegisterService registerService;
	@PostMapping("/register")
	public ResponseEntity<Register> register(@RequestBody Register register)
	{
		Register registerr=registerService.addUser(register);
		return ResponseEntity.status(HttpStatus.CREATED).body(registerr);
	}
}
