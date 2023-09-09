package com.shyamal.employeeservice.service;

import com.shyamal.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="localhost:8080",value = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("/API/department/{id}")
    DepartmentDto getDepartmentById(@PathVariable("id") int id);
    }
