package com.teja.springrestass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class customercontroller {
	@Autowired
	customerservice customerservice;
	
	@RequestMapping(value="/customer", method=RequestMethod.POST)
	public int addcustomer(@RequestBody customer cus) {
		customerservice.addcus(cus);
		return cus.getID();
	}
	@PutMapping("/customers")
	public String updatecus(@RequestBody customer cus ) {
		customerservice.addcus(cus);
		return "success";
	}
	@DeleteMapping("/customers/{ID}")
	public String deletecustomer(@PathVariable ("ID") String ID) {
		customerservice.deletecus(ID);
		return "success";
	}
	@GetMapping("/customers/{ID}")
	public customer findcus(@PathVariable ("ID") int ID) {
		return  customerservice.findbyid(ID);
		
	}
	@GetMapping("/customers")
	public List<customer> findallcus() {
		return customerservice.findall();
	}

}
