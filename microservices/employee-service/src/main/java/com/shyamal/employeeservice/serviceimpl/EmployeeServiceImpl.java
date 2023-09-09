package com.shyamal.employeeservice.serviceimpl;

import com.shyamal.employeeservice.dto.APIResponseDto;
import com.shyamal.employeeservice.dto.DepartmentDto;
import com.shyamal.employeeservice.dto.EmployeeDto;
import com.shyamal.employeeservice.entity.Employee;
import com.shyamal.employeeservice.repository.EmployeeRepository;
import com.shyamal.employeeservice.service.APIClient;
import com.shyamal.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private APIClient apiClient;
    private APIResponseDto apiResponseDto;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee emp=new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentId()
        );
       Employee empsaved = employeeRepository.save(emp);

        EmployeeDto empDto=new EmployeeDto(
                empsaved.getId(),
                empsaved.getFirstName(),
                empsaved.getLastName(),
                empsaved.getEmail(),
                empsaved.getDepartmentId()
        );
        return  empDto;
    }

    @Override
    public APIResponseDto getEmployeeById(int employeeId) {

        Employee emp=employeeRepository.findById(employeeId).get();

        DepartmentDto deptDto= apiClient.getDepartmentById(emp.getDepartmentId());

        EmployeeDto empDto=new EmployeeDto(
                emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getDepartmentId()
        );
        apiResponseDto=new APIResponseDto();
            apiResponseDto.setEmployee(empDto);
            apiResponseDto.setDepartment(deptDto);
        return apiResponseDto;
    }
}
