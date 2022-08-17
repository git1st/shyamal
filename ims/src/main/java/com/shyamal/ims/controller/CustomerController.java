package com.shyamal.ims.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shyamal.ims.model.Book;
import com.shyamal.ims.model.Broker;
import com.shyamal.ims.model.Customer;
import com.shyamal.ims.repository.BrokerRepository;
import com.shyamal.ims.repository.CustomerRepository;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired CustomerRepository customerRepo;
	@Autowired BrokerRepository brokerRepo;
	
	@ModelAttribute(name = "brokers")
	public List<Broker> getBrokers()
	{
		return brokerRepo.findAll();

	}
	
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String showCustomer(Model model)
	{
		model.addAttribute("customers",customerRepo.findAll());
		return "customer/list";
	}
	
	@RequestMapping(value = {"/add"}, method = RequestMethod.GET)
	public String addCustomer(Model model)
	{
		Customer customer=new Customer();
		model.addAttribute("customer",customer);
		return "customer/form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCustomer(@Valid Customer customer, BindingResult bindingResult, final RedirectAttributes redirectAttributes)
	{
		customerRepo.save(customer);
		redirectAttributes.addFlashAttribute("successMsg", "'" + customer.getFirstname() + "' is added as a new broker.");
		return "redirect:/customer/list/";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editCustomer(@PathVariable (name = "id") Long id, Model model)
	{
		Customer customer=customerRepo.findById(id).get();
		if(customer!=null)
		{
			model.addAttribute("customer",customer);
			return "customer/form";
		}
		else
		{
			return "customer/add";
		}
	}
	
	@RequestMapping(value = "/remove/{id}",method = RequestMethod.GET)
	public String removeCustomer(@PathVariable (name = "id") Long id)
	{
		customerRepo.deleteById(id);
		return "redirect:/customer/list";
	}
}
