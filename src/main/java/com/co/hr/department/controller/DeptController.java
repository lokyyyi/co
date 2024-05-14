package com.co.hr.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.hr.department.dto.DepartmentDto;
import com.co.hr.department.entity.Department;
import com.co.hr.department.service.DeptService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DeptController {
	
	@Autowired
	private DeptService deptService; 
	
	@PostMapping("/dept/insertDept")
	public Department insertDept(DepartmentDto dto) {
		return deptService.insertDept(dto);
	}
	
	@PostMapping("/dept/update")
	public void update(DepartmentDto dto) {
		deptService.update(dto);
	}
	
	@PostMapping("/dept/delete")
	public void delete(Long id) {
		deptService.delete(id);
	}
		
	

}
