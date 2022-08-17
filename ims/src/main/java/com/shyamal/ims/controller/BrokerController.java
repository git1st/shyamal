package com.shyamal.ims.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shyamal.ims.model.Broker;
import com.shyamal.ims.model.Category;
import com.shyamal.ims.repository.BrokerRepository;

@Controller
@RequestMapping(value = "/broker")
public class BrokerController {

	@Autowired
	public BrokerRepository brokerRepo;
	
	@RequestMapping(value = {"/","/list"}, method = RequestMethod.GET)
	public String showBrokers(Model model)
	{
		model.addAttribute("brokers",brokerRepo.findAll());
		return "broker/list";
	}
	
	@RequestMapping(value = "/add" ,method = RequestMethod.GET)
	public String addBroker(Model model)
	{
		model.addAttribute("broker", new Broker());
		return "broker/form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBroker(@Valid Broker broker, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
			brokerRepo.save(broker);
			redirectAttributes.addFlashAttribute("successMsg", "'" + broker.getFirstname() + "' is added as a new broker.");
			return "redirect:/broker/list";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String EditBroker(@PathVariable (name = "id") Long id, Model model) {
		Broker broker=brokerRepo.findById(id).get();
		if(broker!=null)
		{
			model.addAttribute("broker",broker);
			return "broker/form";
		}
		else 
		{
			return "broker/add";
		}
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String removeBroker(@PathVariable("id") Long id, Model model)
	{
		brokerRepo.deleteById(id);
		return "redirect:/broker/list";
	}
}
