package com.co.hr.department.controller;

import com.co.hr.common.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.hr.department.dto.DepartmentDto;
import com.co.hr.department.entity.Department;
import com.co.hr.department.service.DeptService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {
	
	@Autowired
	private DeptService deptService; 
	
	@PostMapping("/insertDept")
	public ResponseEntity<ResultDto<String>> insertDept(@RequestBody DepartmentDto dto) {
		return deptService.insertDept(dto);
	}
	
	@PostMapping("/update")
	public void update(DepartmentDto dto) {
		deptService.update(dto);
	}
	
	@PostMapping("/delete")
	public void delete(Long id) {
		deptService.delete(id);
	}

	@PostMapping("/insertDepts")
	public ResponseEntity<ResultDto<String>> insertDepts(@RequestBody List<DepartmentDto> dtos) {
		return deptService.insertDepts(dtos);
	}
	

}
