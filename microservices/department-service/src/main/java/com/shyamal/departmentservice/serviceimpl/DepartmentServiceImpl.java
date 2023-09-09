package com.shyamal.departmentservice.serviceimpl;

import com.shyamal.departmentservice.dto.DepartmentDto;
import com.shyamal.departmentservice.entity.Department;
import com.shyamal.departmentservice.repository.DepartmentRepository;
import com.shyamal.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department dept=new Department(
                departmentDto.getId(),
                departmentDto.getName(),
                departmentDto.getDepartmentcode(),
                departmentDto.getDescription()
        );
        Department savedDept=departmentRepository.save(dept);
       // return departmentRepository.save(departmentDto);

        DepartmentDto deptDto=new DepartmentDto(
                savedDept.getId(),
                savedDept.getName(),
                savedDept.getDepartmentcode(),
                savedDept.getDescription()
        );
        return deptDto;
    }

    @Override
    public DepartmentDto getDepartmentById(int departmentId) {
        Department savedDept=departmentRepository.findById(departmentId).get();
        DepartmentDto deptDto=new DepartmentDto(
                savedDept.getId(),
                savedDept.getName(),
                savedDept.getDepartmentcode(),
                savedDept.getDescription()
        );
        return deptDto;
    }
}
