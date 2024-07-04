package com.co.hr.department.entity;

import java.util.ArrayList;
import java.util.List;

import com.co.hr.department.dto.DepartmentDto;
import com.co.hr.department.dto.TempDepartmentDto;
import com.co.hr.staff.entity.Staff;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TempDepartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tempdepartment_id", columnDefinition = "BIGINT")
    private Long id;
	
	private String deptName;
	private String deptGroup;
	
	private String useYn;

	@OneToMany(mappedBy = "tempDepartment", cascade = CascadeType.ALL)
	private List<Staff> tempStaffs = new ArrayList<>();

	public TempDepartment(TempDepartmentDto e) {
		this.setDeptName(e.getDeptName());
		this.setDeptGroup(e.getDeptGroup());
		this.setUseYn(e.getUseYn());
	}
}
