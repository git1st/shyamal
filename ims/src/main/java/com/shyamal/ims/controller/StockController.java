package com.shyamal.ims.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.http.HttpHeaders;
import java.sql.Connection;
import java.sql.ConnectionBuilder;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shyamal.ims.model.Product;
import com.shyamal.ims.model.Stock;
import com.shyamal.ims.repository.ProductRepository;
import com.shyamal.ims.repository.StockRepository;
import com.shyamal.ims.service.StockService;

import javassist.ClassPath;
import net.bytebuddy.asm.Advice.Return;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@Controller
@RequestMapping(value = "/stock")
public class StockController {
	
	@Autowired
	private StockRepository stockRepo;
	@Autowired
	private ProductRepository productRepo; 
	@Autowired
	private StockService stockService;
	
	@ModelAttribute(value = "products")
	public List<Product> getProducts()
	{
		return productRepo.findAll();
	}
	
	@RequestMapping(value = "/add" , method = RequestMethod.GET)
	public String addStock(Model model)
	{
		model.addAttribute("stock", new Stock());
		return "stock/form";
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String saveStock(Stock stock)
	{
		stockRepo.save(stock);
		return "redirect:/stock/list";
	}

	@RequestMapping(value = {"","/list"},method = RequestMethod.GET)
	public String getStocks(Model model)
	{
		model.addAttribute("stocks",stockRepo.findAll());
		return "stock/list";
	}
	
	
	@RequestMapping(value = "/edit/{id}",method = RequestMethod.GET )
	public String editStock(@PathVariable(name = "id") Long id, Model model) {
		Stock stock=stockRepo.findById(id).get();
		model.addAttribute("stock",stock);
		return "stock/form";
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String deleteStock(@PathVariable(name = "id") Long id)
	{
		stockRepo.deleteById(id);
		return "redirect:/stock/list";
	}
	
	  @RequestMapping(value = "/availableStockList", method = RequestMethod.GET)
	  public String getTotalStocks(Model model) throws JRException, FileNotFoundException {
			model.addAttribute("avilablestocks",stockRepo.getCustomrData());
			return "stock/availableStockList";
		}
	
	///report
	/*  @RequestMapping(value = "/availableStockList", method = RequestMethod.GET)
	  public String getTotalStocks(Model model) throws JRException, FileNotFoundException {
			/*String reportfile="/opt/reports/report1.jrxml";
			File is=ResourceUtils.getFile(reportfile);
			JasperReport jasperReport = JasperCompileManager.compileReport(is.getAbsolutePath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(stockRepo.findAll(),false));
			//JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(stockRepo.getCustomrData(), false));
			JasperExportManager.exportReportToPdfFile(jasperPrint, "/opt/repot.pdf");
			
		  
			return "/stock/list";
		}*/
	}