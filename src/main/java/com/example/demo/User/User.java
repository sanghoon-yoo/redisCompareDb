package com.example.demo.User;

public class User {
	
	private int id;
	private String customer;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", customer=" + customer + "]";
	}


}
