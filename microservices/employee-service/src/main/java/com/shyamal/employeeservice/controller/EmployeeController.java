package com.shyamal.employeeservice.controller;

import com.shyamal.employeeservice.dto.APIResponseDto;
import com.shyamal.employeeservice.dto.EmployeeDto;
import com.shyamal.employeeservice.entity.Employee;
import com.shyamal.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("save")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
            EmployeeDto empDto = employeeService.saveEmployee(employeeDto);
         return new ResponseEntity<>(empDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") int employeeId){
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(apiResponseDto);
    }
}
