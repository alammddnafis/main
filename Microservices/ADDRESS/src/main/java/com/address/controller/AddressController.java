package com.address.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.address.model.dto.AddressDto;
import com.address.model.dto.AddressRequest;
import com.address.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {
	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<List<AddressDto>> saveAddress(@RequestBody AddressRequest addressRequest)
	{
		List<AddressDto> response=addressService.saveAddress(addressRequest);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<List<AddressDto>> updateAddress(@RequestBody AddressRequest addressRequest)
	{
		List<AddressDto> response=addressService.updateAddress(addressRequest);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/all-address")
	public ResponseEntity<List<AddressDto>> getAllAddress()
	{
		List<AddressDto> response=addressService.getAllAddress();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<AddressDto> getAddressById(@PathVariable Long addressId)
	{
		AddressDto response=addressService.getSingleAddress(addressId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable Long addressId)
	{
		addressService.deleteAddress(addressId);
		return new ResponseEntity<>("Address deleted successfully!",HttpStatus.OK);
	}
}
