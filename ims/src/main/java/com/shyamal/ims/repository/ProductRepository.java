package com.shyamal.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shyamal.ims.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
