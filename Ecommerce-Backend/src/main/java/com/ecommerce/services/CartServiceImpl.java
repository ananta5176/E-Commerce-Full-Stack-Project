package com.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.daos.CartDao;
import com.ecommerce.entities.Cart;

@Service
public class CartServiceImpl implements CartService {

	@Autowired private CartDao dao;

	@Override
	public void addToCart(Cart cart) {
		dao.save(cart);
	}

	@Override
	public List<Cart> findByUser(int id) {
		// TODO Auto-generated method stub
		return dao.findByCustomerId(id);
	}

	@Override
	public void deleteFromCart(int cartid) {
		// TODO Auto-generated method stub
		dao.deleteById(cartid);
	}

	@Override
	public boolean checkItemInCart(int prodid,int id) {
		// TODO Auto-generated method stub
		return dao.findItemInCart(prodid,id).isPresent();
	}

	@Override
	public void emptyCart(int id) {
		// TODO Auto-generated method stub
		dao.emptyCart(id);
	}

	@Override
	public void updateQty(int id, int qty) {
		// TODO Auto-generated method stub
		Cart cart = dao.findById(id).get();
		cart.setQty(qty);
		dao.save(cart);
	}

}
