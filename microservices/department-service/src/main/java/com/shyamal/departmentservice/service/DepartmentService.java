package com.shyamal.departmentservice.service;

import com.shyamal.departmentservice.dto.DepartmentDto;
import com.shyamal.departmentservice.entity.Department;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(int departmentId);
}
