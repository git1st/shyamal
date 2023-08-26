package com.shyamal.powerledger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyamal.powerledger.model.Battery;
import com.shyamal.powerledger.repository.BatteryRepository;

@Service
public class BatteryService {
	@Autowired
	private BatteryRepository batteryRepository;
	
	public List<Battery> addBatteries(List<Battery> batteries){
		return batteryRepository.saveAll(batteries);
	}

	//All Battery List
	public List<Battery> getAllBatteries(){
		return batteryRepository.findAll();
	}
	
	//Lis of Battery within given postcode range
	public List<Object> getBatteryListByPostcodeRange(int postcodefrom, int postcodeto){
		return batteryRepository.getBatteryList(postcodefrom,postcodeto);
	}
	
	//Battery with maximum watt capacity between given postcode range
	public List<Object> getBatteryMaxWattCapacityByPostcodeRange(int postcodefrom, int postcodeto){
		return batteryRepository.getBatteryWithMaxWattCapacity(postcodefrom,postcodeto);
	}
	
	//Battery with minimum watt capacity between given postcode range
	public List<Object> getBatteryMinWattCapacityByPostcodeRange(int postcodefrom, int postcodeto){
		return batteryRepository.getBatteryWithMinWattCapacity(postcodefrom,postcodeto);
	}
	
	//List of Battery below the certain capacity threshold 
	public List<Object> getBatteryListBelowThresholdCapacity(int threshold){
		return batteryRepository.getBatteryListBelowCapacity(threshold);
	}
}
