package com.example.customer.myentity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id

	private int customer_id;
	
	private String customerName;
	
	private String customerCountry;

	
	
	public Customer() {
		
	}

	

	public Customer(int customer_id, String customerName, String customerCountry) {
		super();
		this.customer_id = customer_id;
		this.customerName = customerName;
		this.customerCountry = customerCountry;
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



	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", customerName=" + customerName + ", customerCountry="
				+ customerCountry + "]";
	}
	

}
