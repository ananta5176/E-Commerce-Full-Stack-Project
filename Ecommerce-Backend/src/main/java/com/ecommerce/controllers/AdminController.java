package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.Admin;
import com.ecommerce.entities.Customer;
import com.ecommerce.models.Dashboard;
import com.ecommerce.models.LoginDTO;
import com.ecommerce.models.LoginResponse;
import com.ecommerce.models.Response;
import com.ecommerce.services.AdminService;
import com.ecommerce.services.CustomerService;
import com.ecommerce.services.OrderService;
import com.ecommerce.services.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired private AdminService adminService;
	@Autowired private CustomerService customerService;
	@Autowired private ProductService pservice;
	@Autowired private OrderService oservice;
	
	@PostMapping("/validate")
	public ResponseEntity<?> validateUser(@RequestBody LoginDTO dto) {
		System.out.println(dto);
		Admin admin=adminService.validate(dto.getUserid(),dto.getPwd());
		Customer user=customerService.validate(dto.getUserid(),dto.getPwd());
		LoginResponse response;
		if(admin!=null) 
			response=new LoginResponse(0,admin.getUserid(),admin.getUname(),"admin");
		else if(user!=null)
			response=new LoginResponse(user.getId(), user.getUserid(), user.getName(), "Customer");
		else
			return Response.status(HttpStatus.NOT_FOUND);
		return Response.success(response);
	}
	
	@GetMapping("/dashboard")
	public Dashboard dashboard() {
		Dashboard db=new Dashboard();
		db.setCustomers(customerService.allCustomers().size());
		db.setProducts(pservice.allProducts().size());
		db.setOrders(oservice.getAllOrders().size());
		return db;
	}
	
	@PostMapping
	public ResponseEntity<?> updateProfile(@RequestBody Admin admin) {
		adminService.updateAdmin(admin);
		return Response.status(HttpStatus.OK);
	}

}
