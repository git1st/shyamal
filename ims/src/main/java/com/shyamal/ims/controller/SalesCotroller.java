package com.shyamal.ims.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shyamal.ims.model.Broker;
import com.shyamal.ims.model.Product;
import com.shyamal.ims.repository.BrokerRepository;
import com.shyamal.ims.repository.ProductRepository;
import com.shyamal.ims.repository.SalesRepository;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping(value = "/sales")
public class SalesCotroller {
	
	@Autowired
	BrokerRepository brokerRepo;
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	SalesRepository salesRepo;
	
	@ModelAttribute(value = "brokers")
	public List<Broker> getBrokers(){
		return brokerRepo.findAll();
	}
	
	@ModelAttribute(value = "products")
	public List<Product> getProducts(){
		return productRepo.findAll();
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newSales(){
		return "sales/form";
	}
	
	  @RequestMapping(value = "/list", method = RequestMethod.GET)
	  public String getSalesDetails(Model model) throws JRException, FileNotFoundException {
			model.addAttribute("salesdetails",salesRepo.getSalesDetailsData());
			return "sales/salesDetails";
		}
}
