package com.ecommerce.services;

import com.ecommerce.entities.Address;

public interface AddressService {
	Address saveAddress(Address address);
	Address findAddress(int id);
}
