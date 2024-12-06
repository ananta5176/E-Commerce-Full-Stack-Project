package com.ecommerce.services;

import java.util.List;

import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Order;

public interface OrderService {

	Order saveOrder(Order order);
	List<Order> getAllOrders();
	List<Order> getCustomerOrders(Customer customer);
	Order findById(int id);
}