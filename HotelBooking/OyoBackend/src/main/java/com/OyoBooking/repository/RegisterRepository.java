package com.OyoBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OyoBooking.entity.Register;

public interface RegisterRepository extends JpaRepository<Register, Integer>{

	Register save(Register register);

	

}
