package com.co.hr.department.service;

import com.co.hr.common.dto.ResultDto;
import com.co.hr.exception.BadRequestException;
import com.co.hr.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.hr.department.dto.DepartmentDto;
import com.co.hr.department.entity.Department;
import com.co.hr.department.repository.DeptRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeptService {

	@Autowired
	private final DeptRepository deptRepository;
	private final ObjectMapper objectMapper;
	
	public ResponseEntity<ResultDto<String>> insertDept(DepartmentDto dto) {
		try {
			Department dept = new Department(dto);
			System.out.println(dto.getDeptName());
			deptRepository.save(dept);
		} catch (Exception e) {
			throw new BadRequestException("부서 정보 입력이 잘못되었습니다.", ErrorCode.BAD_REQUEST);
		}
		return ResponseEntity.ok(ResultDto.res(200, HttpStatus.OK.toString(), "부서 등록 완료"));
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

	public ResponseEntity<ResultDto<String>> insertDepts(List<DepartmentDto> dtos) {
		for(DepartmentDto dto : dtos) {
			try {
				Department dept = new Department(dto);
				System.out.println(dto.getDeptName());
				deptRepository.save(dept);
			} catch (Exception e) {
				throw new BadRequestException(dto.getDeptName() + "부서 정보 입력이 잘못되었습니다.", ErrorCode.BAD_REQUEST);
			}
		}
		return ResponseEntity.ok(ResultDto.res(200, HttpStatus.OK.toString(), "부서 등록 완료"));
	}
}
