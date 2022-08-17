package com.shyamal.ims.controller;

import java.awt.image.RescaleOp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shyamal.ims.model.Product;
import com.shyamal.ims.repository.ProductRepository;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired 
	ProductRepository productRepo;
	
	@RequestMapping(value = {"","/list"},method = RequestMethod.GET)
	public String showProduct(Model model)
	{
		model.addAttribute("products",productRepo.findAll());
		return "product/list";
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String addProduct(Model model)
	{
		model.addAttribute("product",new Product());
		return "product/form";
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String saveProduct(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{
		productRepo.save(product);
		return "redirect:/product/list";
	}
	
	@RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
	public String editProduct(@PathVariable (name = "id") Long id, Model model)
	{
		Product product=productRepo.findById(id).get();
		model.addAttribute("product",product);
		return "product/form";
	}
	
	@RequestMapping(value = "/remove/{id}",method = RequestMethod.GET)
	public String removeProduct(@PathVariable (name = "id") Long id)
	{
		productRepo.deleteById(id);
		return "redirect:/product/list";
	}

}
