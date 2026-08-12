package com.OyoBooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OyoBooking.entity.Register;
import com.OyoBooking.repository.RegisterRepository;
@Service
public class RegisterService {
	@Autowired
	RegisterRepository registerRepository;
	public Register addUser(Register register) {
		return registerRepository.save(register);		
	}

}
