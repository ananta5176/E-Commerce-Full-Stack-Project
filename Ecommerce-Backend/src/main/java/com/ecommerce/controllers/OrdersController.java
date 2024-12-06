package com.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.Address;
import com.ecommerce.entities.Cart;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Order;
import com.ecommerce.entities.OrderDetails;
import com.ecommerce.entities.Payment;
import com.ecommerce.entities.Product;
import com.ecommerce.models.OrderDetailsDTO;
import com.ecommerce.models.OrderResponseDTO;
import com.ecommerce.models.PlaceOrderDTO;
import com.ecommerce.models.Response;
import com.ecommerce.services.AddressService;
import com.ecommerce.services.CartService;
import com.ecommerce.services.CustomerService;
import com.ecommerce.services.OrderService;
import com.ecommerce.services.OrderdetailService;
import com.ecommerce.services.PaymentService;
import com.ecommerce.services.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired OrderService orderService;
	@Autowired CustomerService customerService;
	@Autowired AddressService addressService;
	@Autowired PaymentService paymentService;
	@Autowired OrderdetailService orderDetailsService;	
	@Autowired ProductService pservice;	
	@Autowired CartService cartservice;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PlaceOrderDTO dto) {	
		Address address=addressService.saveAddress(dto.getAddress());
		Payment payment=paymentService.savePayment(dto.getPayment());
		Order order=new Order();
		order.setAddress(address);
		order.setPayment(payment);
		Customer customer=customerService.findById(dto.getCustomerid());
		order.setCustomer(customer);
		Order orders=orderService.saveOrder(order);
		List<Cart> items = cartservice.findByUser(dto.getCustomerid());
		for(Cart cart : items) {
			OrderDetails od=new OrderDetails();
			od.setOrder(orders);
			od.setQty(cart.getQty());
			Product product=cart.getProduct();
			od.setProduct(product);			
			orderDetailsService.saveOrderDetails(od);
		}
		
		cartservice.emptyCart(dto.getCustomerid());
		
		return Response.status(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllOrders(Optional<Integer> custid) {
		List<Order> result=null;
		if(custid.isPresent()) {
			Customer customer=customerService.findById(custid.get());
			 result= orderService.getCustomerOrders(customer);
		}else {
			result = orderService.getAllOrders();
		}
		return ResponseEntity.ok(result);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOrderById(@PathVariable("id") int id) {
		Order order = orderService.findById(id);
		List<OrderDetails> details=orderDetailsService.findByOrder(order);
		List<OrderDetailsDTO> detailsdto=new ArrayList<OrderDetailsDTO>();
		details.forEach(od -> {
			OrderDetailsDTO dto=OrderDetailsDTO.fromEntity(od);
			detailsdto.add(dto);
		});
		OrderResponseDTO result=new OrderResponseDTO();
		result.setOrder(order);
		result.setDetails(detailsdto);
		return Response.success(result);
	}
}
