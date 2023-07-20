package com.example.customer.mycontroller;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.customer.myentity.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.response.CustomerDTO;
import com.example.customer.response.CustomerResponse;
import com.example.customer.response.OrderResponse;
import com.example.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RestTemplate restTemplate;
	
	/*
	 * get one customer using customerId
	 */
	@GetMapping("/customer/{customer_id}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable int customer_id) {
		CustomerResponse customerById = customerService.getCustomerById(customer_id);
		return ResponseEntity.status(HttpStatus.OK).body(customerById);
	}

	/*
	 * saving new customer along with order
	 */
	
	@PostMapping("/save-customer")
	public void saveCustomer(@RequestBody CustomerResponse customerResponse){
		
		 customerService.saveCustomer(customerResponse);
		 
	}

	/*
	 *updating existing customer and order 
	 */
	
	@PutMapping("/updateCustomer/{customer_id}")
	public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable int customer_id, @RequestBody CustomerResponse customerResponse){
		CustomerResponse updateCustomer = customerService.updateCustomer(customer_id, customerResponse);	
		return ResponseEntity.status(HttpStatus.OK).body(updateCustomer);
	}
	/*
	 * deleting customer
	 */
	
	@DeleteMapping("/deleteCustomer/{customer_id}")
	public void deleteCustomerAndOrder(@PathVariable int customer_id) {
		System.out.println("Deleting customer Id "+customer_id);
		customerService.deleteCustomerAndOrder(customer_id);
	}
	
	@GetMapping("/customerTest/{customer_id}")
	public ResponseEntity<CustomerDTO> getCustomerAndOrdersById(@PathVariable int customer_id) {
		Customer findById = customerRepository.findById(customer_id).get();
		CustomerDTO customerResponse = modelMapper.map(findById, CustomerDTO.class);
		List<OrderResponse> orderResponse = Arrays.asList(restTemplate.getForObject("http://localhost:8082/order-app/api/orders/10", OrderResponse[].class, customer_id));
		customerResponse.setOrderResponse(orderResponse);
		System.out.println("Testing customer Reponse "+customerResponse);
		return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
	}
	
	//new testing method for pagination
	@GetMapping("/customerTest123/{customer_id}")
	public ResponseEntity<CustomerDTO> getCustomerAndOrdersByIdTest(@PathVariable int customer_id,@RequestParam int pageNumber
			,@RequestParam int pageSize) {
		
		Customer findById = customerRepository.findById(customer_id).get();
		CustomerDTO customerDTO = modelMapper.map(findById, CustomerDTO.class);
		List<OrderResponse> orderResponse = Arrays.asList(restTemplate.getForObject("http://localhost:8082/order-app/api/getAllOrders/customer_id", 
				OrderResponse.class, customer_id));
		customerDTO.setOrderResponse(orderResponse);
		System.out.println("Testing customer Reponse "+customerDTO);
		return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
	}
	
	//need to check 
	@GetMapping("/getOnlyCustomers")
	public ResponseEntity<Page<Customer>> getOnlyCustomersDetails(@RequestParam int pageNumber, @RequestParam int pageSize){
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<Customer> customers = customerRepository.findAll(pageRequest);
		return ResponseEntity.status(HttpStatus.OK).body(customers);
	}
	
}




