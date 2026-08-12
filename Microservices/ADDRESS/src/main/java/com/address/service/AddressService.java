package com.address.service;

import java.util.List;

import com.address.model.dto.AddressDto;
import com.address.model.dto.AddressRequest;

public interface AddressService {
	List<AddressDto> saveAddress(AddressRequest addressRequest);
	List<AddressDto> updateAddress(AddressRequest addressRequest);
	AddressDto getSingleAddress(Long id);
	List<AddressDto> getAllAddress();
	void deleteAddress(Long id);
}
