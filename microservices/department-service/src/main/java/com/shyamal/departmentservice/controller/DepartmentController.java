package com.shyamal.departmentservice.controller;

import com.shyamal.departmentservice.dto.DepartmentDto;
import com.shyamal.departmentservice.entity.Department;
import com.shyamal.departmentservice.service.DepartmentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    @PostMapping("save")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto deptDto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(deptDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity <DepartmentDto> getDepartmentById(@PathVariable("id") int id){
        DepartmentDto dept = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(dept);
    }
}
