package com.shyamal.ims.restcontroller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shyamal.ims.model.Customer;
import com.shyamal.ims.model.Product;
import com.shyamal.ims.model.Sales;
import com.shyamal.ims.model.SalesLine;
import com.shyamal.ims.repository.CustomerRepository;
import com.shyamal.ims.repository.ProductRepository;
import com.shyamal.ims.repository.SalesLineRepository;
import com.shyamal.ims.repository.SalesRepository;

@RestController
@RequestMapping(value = "/rest/sales/")
public class SalesRestController {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	SalesRepository salesRepo;
	
	@Autowired
	SalesLineRepository salesLineRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@RequestParam Map<String, String> payload) throws ParseException {
		
		String customerIdStr = (String)payload.get("customer");
		String[] a =payload.get("products").toString().split(",");
		//String[] quantitys = payload.get("quantitys").toString().split(",");
		//String[] priceperunits = payload.get("prices").toString().split(",");
		String salesdate= (String)payload.get("salesdate");
		String[] productIdsStr = payload.get("products").toString().split(",");
		String[] qtyslist = payload.get("qtys").toString().split(",");
		String[] priceslist = payload.get("prices").toString().split(",");
		
		Long customerId = null;
		List<Long> productIds = new ArrayList<Long>();
		List<BigDecimal> qtys = new ArrayList<BigDecimal>();
		List<BigDecimal> prices = new ArrayList<BigDecimal>();
		try {
			customerId = Long.parseLong( customerIdStr );
			for(int k=0 ; k<productIdsStr.length ; k++) {
				productIds.add( Long.parseLong(productIdsStr[k]) );
			}
			
			for(int k=0 ; k<qtyslist.length ; k++) {
				qtys.add( new BigDecimal(qtyslist[k]) );
			}
			
			for(int k=0 ; k<qtyslist.length ; k++) {
				prices.add( new BigDecimal(priceslist[k]) );
			}
			
			
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return "invalid number format";
		}
		
		Customer customer = customerRepo.getOne( customerId );
		List<Product> products = productRepo.findAllById(productIds);
		
		Date saledate=new SimpleDateFormat("yyyy-MM-dd").parse(salesdate);
		Sales sales=new Sales();
		sales.setCustomer(customer);
		sales.setSalesdate(saledate);
		sales.setCreateDate(new java.util.Date());
		salesRepo.save(sales);
		
		for( int k=0 ; k<products.size() ; k++ ) {
			Product product = products.get(k);
			BigDecimal qty=(qtys.get(k));
			BigDecimal price=(prices.get(k));
			
			SalesLine salesLine = new SalesLine();
			salesLine.setProduct(product);
			salesLine.setCreateDate(new java.util.Date());
			salesLine.setQuantity(qty);
			salesLine.setPrice(price);
			salesLine.setSales(sales);
			salesLineRepo.save(salesLine);
		}
		return "success";
	}
}
