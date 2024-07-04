package com.co.hr.staff.dto;

import com.co.hr.staff.entity.HumanResume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HumanCareerDto {
    private Long id;
    private HumanResume humanResume;
    private String companyName;
    private String joinDate;
    private String resignDate;
    private String career;
}
