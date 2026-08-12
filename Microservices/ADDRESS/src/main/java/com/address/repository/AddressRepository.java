package com.address.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.address.model.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	List<Address> findAllByEmpId(Long empId);
}
