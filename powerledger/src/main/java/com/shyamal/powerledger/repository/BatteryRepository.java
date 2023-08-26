package com.shyamal.powerledger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.shyamal.powerledger.model.Battery;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Integer>{

	@Query(value="SELECT name,postcode,wattcapacity,sum(wattcapacity) over() as totalwattcapacity,round((sum(wattcapacity) over())/(sum(1) over()),2) as averagewattcapacity \n"
			+ " from pl_battery \n"
			+ " where postcode between :postcodefrom and :postcodeto \n"
			+ " order by name",nativeQuery = true)
	public List<Object> getBatteryList(@Param("postcodefrom") int postcodefrom , @Param("postcodeto") int postcodeto);
	
	@Query(value="SELECT name,postcode,wattcapacity \n"
			+ " from pl_battery \n"
			+ " where postcode between :postcodefrom and :postcodeto \n"
			+ " and wattcapacity=(select distinct Max(wattcapacity) from pl_battery where postcode between :postcodefrom and :postcodeto)"
			+ " ",nativeQuery = true)
	public List<Object> getBatteryWithMaxWattCapacity(@Param("postcodefrom") int postcodefrom , @Param("postcodeto") int postcodeto);
		
	@Query(value="SELECT name,postcode,wattcapacity \n"
			+ " from pl_battery \n"
			+ " where postcode between :postcodefrom and :postcodeto \n"
			+ " and wattcapacity=(select distinct Min(wattcapacity) from pl_battery where postcode between :postcodefrom and :postcodeto)"
			+ " ",nativeQuery = true)
	public List<Object> getBatteryWithMinWattCapacity(@Param("postcodefrom") int postcodefrom , @Param("postcodeto") int postcodeto);
	
	@Query(value="SELECT name,postcode,wattcapacity \n"
			+ " from pl_battery \n"
			+ " where wattcapacity<:threshold"
			+ " ",nativeQuery = true)
	public List<Object> getBatteryListBelowCapacity(@Param("threshold") int threshold);
}