package com.example.customer.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.customer.myentity.Customer;
import com.example.customer.response.OrderResponse;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	

//	@Query(nativeQuery = true, value="SELECT o.id, o.order_name, o.order_price FROM `agiliad-project`.orders o join `agiliad-project`.customer c on o.customer_id = c.customer_id where o.customer_id=:customer_id")
//	public OrderResponse  findOrderByCustomerId(int customer_id,PageRequest pageable);
}
