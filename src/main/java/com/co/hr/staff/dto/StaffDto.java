package com.co.hr.staff.dto;

import com.co.hr.department.dto.DepartmentDto;
import com.co.hr.department.dto.TempDepartmentDto;
import com.co.hr.department.entity.Department;
import com.co.hr.department.entity.TempDepartment;
import com.co.hr.staff.entity.Address;
import com.co.hr.staff.entity.Staff;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StaffDto {
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String roles;
    private Department department;
    private TempDepartment tempDepartment;
    private String position;
    private String email;
    private Address address;

}
