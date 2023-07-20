package com.example.customer.response;

import java.util.List;

public class CustomerDTO {
	
		private int customer_id;
		
		private String customerName;
		
		private String customerCountry;

		private List<OrderResponse> orderResponse;

		public CustomerDTO() {

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

		public List<OrderResponse> getOrderResponse() {
			return orderResponse;
		}

		public void setOrderResponse(List<OrderResponse> orderResponse) {
			this.orderResponse = orderResponse;
		}

		

}
