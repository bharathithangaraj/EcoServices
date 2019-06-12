package com.ecotage.vo;

import java.util.List;

public class User {
	
	private String userName;
    private String email;
    private List<String> productName;
    private Double price;
    private Double total;
    private int Quantity;
    
    
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, String email, List<String> productName, Double price, Double total, int quantity) {
		super();
		this.userName = userName;
		this.email = email;
		this.productName = productName;
		this.price = price;
		this.total = total;
		Quantity = quantity;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getProductName() {
		return productName;
	}
	public void setProductName(List<String> productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	@Override
	public String toString() {
		final int maxLen = 10;
		return "User [userName=" + userName + ", email=" + email + ", productName="
				+ (productName != null ? productName.subList(0, Math.min(productName.size(), maxLen)) : null)
				+ ", price=" + price + ", total=" + total + ", Quantity=" + Quantity + "]";
	}
    
    
    

}
