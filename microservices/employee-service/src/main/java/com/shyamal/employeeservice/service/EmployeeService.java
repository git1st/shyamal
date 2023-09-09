package com.shyamal.employeeservice.service;

import com.shyamal.employeeservice.dto.APIResponseDto;
import com.shyamal.employeeservice.dto.EmployeeDto;
import com.shyamal.employeeservice.entity.Employee;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employee);

    APIResponseDto getEmployeeById(int employeeId);
}
