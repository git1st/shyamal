package com.shyamal.ims.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shyamal.ims.model.Book;
import com.shyamal.ims.model.Broker;
import com.shyamal.ims.model.Category;
import com.shyamal.ims.model.Customer;
import com.shyamal.ims.repository.BrokerRepository;
import com.shyamal.ims.repository.CustomerRepository;

@RestController
@RequestMapping(value = "/rest/customer")
public class CustomerRestController {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private BrokerRepository brokerRepo;
	
	
	@GetMapping(value = "/list")
	public List<Customer> get() {
		return customerRepo.findAll();
	}

	
	
	@GetMapping(value = "/{id}")
	public List<Customer> get(@PathVariable(name = "id") Long id) {
		Broker broker = brokerRepo.findById(id).get();
		return customerRepo.findByBroker(broker);
	}
}
