package com.ecommerce.services;

import java.util.List;

import com.ecommerce.entities.Cart;

public interface CartService {
	void addToCart(Cart cart);
	List<Cart> findByUser(int id);
	void deleteFromCart(int cartid);
	boolean checkItemInCart(int prodid,int id);
	void emptyCart(int id);
	void updateQty(int id,int qty);
}
