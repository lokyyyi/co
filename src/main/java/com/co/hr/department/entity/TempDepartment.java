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

import com.co.hr.staff.entity.Staff;

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

}
