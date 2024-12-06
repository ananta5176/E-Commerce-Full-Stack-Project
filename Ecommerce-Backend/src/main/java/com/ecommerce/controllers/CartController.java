package com.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.Cart;
import com.ecommerce.models.Response;
import com.ecommerce.services.CartService;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired private CartService service;
	
	@PostMapping
	public ResponseEntity<?> addItemToCart(@RequestBody Cart cart) throws Exception {
		if(service.checkItemInCart(cart.getProduct().getProdid(),cart.getCustomer().getId())) {
			throw new Exception("Item already in cart");
		}
		service.addToCart(cart);
		return Response.success("Item added to cart");
	}
	
	@GetMapping
	public List<Cart> listall(int custid){
		return service.findByUser(custid);
	}
	
	@PutMapping("{id}/{qty}")
	public ResponseEntity<?> updateQty(@PathVariable("id")int id,@PathVariable("qty") int qty){
		service.updateQty(id, qty);
		return Response.success("Qty updated");
	}
		
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteItemFromCart(@PathVariable("id") int id){
		service.deleteFromCart(id);
		return Response.success("Cart Item deleted successfully");
	}
}
