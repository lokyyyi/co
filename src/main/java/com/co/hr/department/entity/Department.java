package com.co.hr.department.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.transaction.annotation.Transactional;

import com.co.hr.department.dto.DepartmentDto;
import com.co.hr.staff.entity.Staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", columnDefinition = "BIGINT")
    private Long id;
	
	private String deptName;
	private String deptGroup;
	
	private String useYn;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<Staff> staffs = new ArrayList<>();
	
	public Department(DepartmentDto e) {
		this.setDeptName(e.getDeptName());
		this.setDeptGroup(e.getDeptGroup());
		this.setUseYn(e.getUseYn());
	}
	
	@Transactional
	public void update(DepartmentDto e) {
		if(e.getDeptName()!=null) this.setDeptName(e.getDeptName());
		if(e.getDeptGroup()!=null) this.setDeptGroup(e.getDeptGroup());
		if(e.getUseYn()!=null) this.setUseYn(e.getUseYn());
	}
}
