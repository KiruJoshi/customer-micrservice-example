package com.example.customer.response;

public class OrderResponse {

	private int id;
	private String orderName;
	private int orderPrice;
	private int customer_id;

	
	public OrderResponse() {

	}

	public OrderResponse(int id, String orderName, int orderPrice) {
		super();
		this.id = id;
		this.orderName = orderName;
		this.orderPrice = orderPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	@Override
	public String toString() {
		return "OrderResponse [id=" + id + ", orderName=" + orderName + ", orderPrice=" + orderPrice + ", customer_id="
				+ customer_id + "]";
	}

}
