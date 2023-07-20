package com.example.customer.response;

public class CustomerResponse {

	private int customer_id;
	
	private String customerName;
	
	private String customerCountry;

	private OrderResponse orderResponse;

	public CustomerResponse() {

	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCountry() {
		return customerCountry;
	}

	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}

	public OrderResponse getOrderResponse() {
		return orderResponse;
	}

	public void setOrderResponse(OrderResponse orderResponse) {
		this.orderResponse = orderResponse;
	}

	
	
}
