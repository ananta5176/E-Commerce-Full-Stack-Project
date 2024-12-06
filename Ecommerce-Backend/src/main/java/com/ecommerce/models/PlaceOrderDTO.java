package com.ecommerce.models;

import com.ecommerce.entities.Address;
import com.ecommerce.entities.Payment;

public class PlaceOrderDTO {

	private Address address;
	private Payment payment;
	private int customerid;
	
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
}
