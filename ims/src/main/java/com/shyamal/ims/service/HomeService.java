package com.shyamal.ims.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyamal.ims.common.Constants;
import com.shyamal.ims.repository.BrokerRepository;
import com.shyamal.ims.repository.CustomerRepository;
import com.shyamal.ims.repository.ProductRepository;
import com.shyamal.ims.repository.SalesLineRepository;
import com.shyamal.ims.repository.SalesRepository;

@Service
public class HomeService {

	@Autowired
	private BrokerRepository brokerRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private SalesRepository salesRepo;
	
	public Map<String, Long> getTopTilesMap() {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("totalBrokers", brokerRepo.count());
		map.put("totalCustomers", customerRepo.count());
		map.put("totalProducts", productRepo.count());
		map.put("totalSales", salesRepo.count());
		return map;
	}
	
}
