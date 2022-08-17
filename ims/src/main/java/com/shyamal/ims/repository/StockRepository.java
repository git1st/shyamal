package com.shyamal.ims.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.shyamal.ims.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
	
	@Query(value="select dateentry ,p.name as product, sum(quantity) as quantity from stock s join product p on(s.product_id=p.id) group by dateentry,p.name",nativeQuery = true)
	//@Query(value="SELECT dateentry,sum(quantity) as quantity FROM stock",nativeQuery = true)
	public List<Object> getCustomrData();
	
}
