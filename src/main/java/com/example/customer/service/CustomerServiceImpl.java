package com.example.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.customer.myconifg.IdNotFoundException;
import com.example.customer.myentity.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.response.CustomerDTO;
import com.example.customer.response.CustomerResponse;
import com.example.customer.response.OrderResponse;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public CustomerResponse getCustomerById(int customer_id) {
		Optional<Customer> findById = customerRepository.findById(customer_id);
		if (findById.isPresent()) {
			Customer customer = customerRepository.findById(customer_id).get();
			CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
			System.out.println("Printing Customer Response " + customerResponse);

			OrderResponse orderResponse = restTemplate.getForObject(
					"http://localhost:8082/order-app/api/orders/{customer_id}", OrderResponse.class, customer_id);

			customerResponse.setOrderResponse(orderResponse);

			return customerResponse;
		} else {

			throw new IdNotFoundException("customer Id not present");
		}
	}

	@Override
	public List<CustomerResponse> getAllCustomersWithOrders() {
		//PageRequest paging = PageRequest.of(pageNo, pageSize);
		     List<Customer> customers = customerRepository.findAll();
		    List<CustomerResponse> customerResponses = new ArrayList();

		    for (Customer customer : customers) {
		        CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
		        System.out.println("Printing Customer Response: " + customerResponse);

		        OrderResponse orderResponse = restTemplate.getForObject(
		                "http://localhost:8082/order-app/api/getAllOrders/{customer_id}", OrderResponse.class, customer.getCustomer_id());

		        customerResponse.setOrderResponse(orderResponse);
		        customerResponses.add(customerResponse);
		    }

		    return customerResponses;
		}
	
	
	
	public List<CustomerResponse> getAllCustomersWithOrdersTest(int pageNo, int pageSize) {
		PageRequest paging = PageRequest.of(pageNo, pageSize);
		    Page<Customer> customers = customerRepository.findAll(paging);
		    List<CustomerResponse> customerResponses = new ArrayList();

		    for (Customer customer : customers) {
		        CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
		        System.out.println("Printing Customer Response: " + customerResponse);

		        OrderResponse orderResponse = restTemplate.getForObject(
		                "http://localhost:8082/order-app/api/getAllOrders/{customer_id}?pageNo={pageNumber}&pageSize={pageSize}", OrderResponse.class, customer.getCustomer_id());

		        customerResponse.setOrderResponse(orderResponse);
		        customerResponses.add(customerResponse);
		    }

		    return customerResponses;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void saveCustomer(CustomerResponse customerResponse) {
		Customer customer = modelMapper.map(customerResponse, Customer.class);
		OrderResponse orderResponse = customerResponse.getOrderResponse();

		orderResponse.setCustomer_id(customerResponse.getCustomer_id());
		customerRepository.save(customer);
		restTemplate.postForObject("http://localhost:8082/order-app/api/saveOrders", orderResponse,
				OrderResponse.class);

	}

	@Override
	public CustomerResponse updateCustomer(int customer_id, CustomerResponse customerResponse) {
		Optional<Customer> findById = customerRepository.findById(customer_id);
		if (findById.isPresent()) {

			Customer customer = modelMapper.map(customerResponse, Customer.class);
			OrderResponse orderResponse = customerResponse.getOrderResponse();
			int id = orderResponse.getId();
			orderResponse.setCustomer_id(customerResponse.getCustomer_id());// setting foreign key
			customerRepository.save(customer);
			restTemplate.put("http://localhost:8082/order-app/api/updateOrders/{id}", orderResponse, id);
			return customerResponse;

		} else {
			return customerResponse;
		}

	}

	public void deleteCustomerAndOrder(int customer_id) {
		Optional<Customer> findById = customerRepository.findById(customer_id);
		if (findById.isPresent()) {

			restTemplate.delete("http://localhost:8082/order-app/api/deleteOrderTest/{customer_id}", customer_id);
			customerRepository.deleteById(customer_id);
		} else {
			System.out.println("id not found in db");
		}
	}


}
