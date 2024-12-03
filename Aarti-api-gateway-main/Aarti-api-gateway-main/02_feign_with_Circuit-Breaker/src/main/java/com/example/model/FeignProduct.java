package com.example.model;

public class FeignProduct {

	private int id;
	private String pname;
	private double price;

	public FeignProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeignProduct(int id, String pname, double price) {
		super();
		this.id = id;
		this.pname = pname;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public double getPrice() {
		return price;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
