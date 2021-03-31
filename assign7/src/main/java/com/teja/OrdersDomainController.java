package com.teja;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersDomainController {
	
	@Autowired
	private OrdersDomainRepository ordersRepo;

	@GetMapping("/orders")
	public List<orders> getAll(){
		return ordersRepo.findAll();
	}
	
	@PostMapping("/orders")
	public orders createOrder(@RequestBody orders orders) {
		ordersRepo.save(orders);
		return orders;
	}
	
	@GetMapping("/orders/{id}")
	public Optional<orders> getOrderById(@PathVariable("id") String id){
		return ordersRepo.findById(id);
	}
	
	@PutMapping("/orders/{id}")
	public orders updateOrderById(@PathVariable("id") String id, @RequestBody orders orders) {
		orders.setOrderId(id);
		ordersRepo.save(orders);
		return orders;
	}
	
	@DeleteMapping("/orders/{id}")
	public String deleteOrders(@PathVariable("id") String id) {
		ordersRepo.deleteById(id);
		return id;
	}
}
