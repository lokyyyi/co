package com.co.hr.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.hr.department.entity.Department;

import java.util.Optional;

public interface DeptRepository extends JpaRepository<Department, Long>{

}
