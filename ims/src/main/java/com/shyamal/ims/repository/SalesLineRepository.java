package com.shyamal.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shyamal.ims.model.SalesLine;

@Repository
public interface SalesLineRepository extends JpaRepository<SalesLine, Long>{

}
