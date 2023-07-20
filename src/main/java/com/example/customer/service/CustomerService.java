package com.example.customer.service;

import java.util.List;

import com.example.customer.response.CustomerResponse;

public interface CustomerService {
	
	public CustomerResponse getCustomerById(int customer_id);
	
	public void saveCustomer(CustomerResponse customerResponse);
	
	public CustomerResponse updateCustomer(int customer_id, CustomerResponse customerResponse);

	//public void deleteCustomerAndOrder(int customer_id);

	public void deleteCustomerAndOrder(int customer_id);
	
	public List<CustomerResponse> getAllCustomersWithOrders();
}
