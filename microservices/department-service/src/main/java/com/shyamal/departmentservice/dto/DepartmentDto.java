package com.shyamal.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
        private int id;
        private String name;
        private String departmentcode;
        private String description;
 }
