package com.shyamal.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shyamal.ims.model.Broker;

@Repository
public interface BrokerRepository extends JpaRepository<Broker,Long>{
	

}
