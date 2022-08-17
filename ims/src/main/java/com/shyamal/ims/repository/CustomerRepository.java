package com.shyamal.ims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shyamal.ims.model.Broker;
import com.shyamal.ims.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public  List<Customer> findByBroker(Broker broker);
	
	//@Query(value="select * from customer where broker_id= ",nativeQuery = true)
	//public List<Customer> getCustomrData();

}
