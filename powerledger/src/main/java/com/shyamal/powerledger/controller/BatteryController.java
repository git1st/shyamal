package com.shyamal.powerledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shyamal.powerledger.model.Battery;
import com.shyamal.powerledger.service.BatteryService;

@RestController
@RequestMapping("/API/")
public class BatteryController {
	@Autowired
	private BatteryService batteryService;
	
	@PostMapping("/addBatteries")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Battery> Save(@RequestBody List<Battery> batteries){
		return batteryService.addBatteries(batteries);
	}
	
	@GetMapping("/getBatteryList")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Battery>  getAllBatteries() {
		return batteryService.getAllBatteries();
	}
	
	@GetMapping("/getBatteryList/{postcodefrom}/{postcodeto}")
	 @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public List<Object>  getBatteryList(@PathVariable int postcodefrom, @PathVariable int postcodeto) {
		return batteryService.getBatteryListByPostcodeRange(postcodefrom,postcodeto);
	}

	@GetMapping("/getBatteryWithMaxWattCapacity/{postcodefrom}/{postcodeto}")
	 @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public List<Object>  getBatteryWithMaxWattCapacity(@PathVariable int postcodefrom, @PathVariable int postcodeto) {
		return batteryService.getBatteryMaxWattCapacityByPostcodeRange(postcodefrom,postcodeto);
	}
	
	@GetMapping("/getBatteryWithMinWattCapacity/{postcodefrom}/{postcodeto}")
	 @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public List<Object>  getBatteryWithMinWattCapacity(@PathVariable int postcodefrom, @PathVariable int postcodeto) {
		return batteryService.getBatteryMinWattCapacityByPostcodeRange(postcodefrom,postcodeto);
	}
	
	@GetMapping("/getBatteryListBelowCapacity/{threshold}")
	 @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public List<Object>  getBatteryListBelowThreshold(@PathVariable int threshold) {
		return batteryService.getBatteryListBelowThresholdCapacity(threshold);
	}
}
