package com.ecommerce.models;

public class Dashboard {
	private int categories;
	private int products;
	private int customers;
	private int vendors;
	private int orders;
	public int getCategories() {
		return categories;
	}
	public void setCategories(int categories) {
		this.categories = categories;
	}
	public int getProducts() {
		return products;
	}
	public void setProducts(int products) {
		this.products = products;
	}
	public int getCustomers() {
		return customers;
	}
	public void setCustomers(int customers) {
		this.customers = customers;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public int getVendors() {
		return vendors;
	}
	public void setVendors(int vendors) {
		this.vendors = vendors;
	}
	
	
}
