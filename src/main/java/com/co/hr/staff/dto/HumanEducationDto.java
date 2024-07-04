package com.co.hr.staff.dto;

import com.co.hr.staff.entity.HumanResume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HumanEducationDto {
    private Long id;
    private HumanResume humanResume;
    private String educationType;
    private String educationName;
    private String admissionYear;
    private String graduationYear;
}
