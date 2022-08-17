package com.shyamal.ims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shyamal.ims.model.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long>{
	
	@Query(value="SELECT s.salesdate,c.firstname as customer,p.name as product,sl.quantity,sl.price,round((sl.quantity*sl.price),2) as totalamt\n"
			+ "FROM sales s\n"
			+ "join salesline sl on(s.id=sl.sales_id)\n"
			+ "join customer c on(c.id=s.customer_id)\n"
			+ "join product p on(p.id=product_id)  \n"
			+ "ORDER BY s.salesdate",nativeQuery = true)
	public List<Object> getSalesDetailsData();

}
