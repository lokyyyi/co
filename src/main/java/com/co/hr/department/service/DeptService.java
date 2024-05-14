package com.co.hr.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.hr.department.dto.DepartmentDto;
import com.co.hr.department.entity.Department;
import com.co.hr.department.repository.DeptRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeptService {

	@Autowired
	private DeptRepository deptRepository;
	
	public Department insertDept(DepartmentDto dto) {
		Department dept = new Department(dto);
		return deptRepository.save(dept);
	}
	
	@Transactional
	public void update(DepartmentDto dto) {
		Department dept = deptRepository.findById(dto.getId()).get();
		dept.update(dto);
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			deptRepository.delete(deptRepository.findById(id).get());
		} catch (Exception e) {
			return;
		}
	}
}
