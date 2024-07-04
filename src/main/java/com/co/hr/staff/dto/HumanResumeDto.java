package com.co.hr.staff.dto;

import com.co.hr.staff.entity.HumanCareer;
import com.co.hr.staff.entity.HumanEducation;
import com.co.hr.staff.entity.HumanResume;
import com.co.hr.staff.entity.HumanResumeAttachment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HumanResumeDto {
    private Long id;
    private String name;
    private String socialSecurityNumber;
    private String gender;
    private String email;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipcode;
    private String hopingSalary;
    private Date enterResumeDate;
    private String passOrNot;
    private List<HumanEducationDto> humanEducationDto;
    private List<HumanResumeAttachmentDto> humanResumeAttachmentDto;
    private List<HumanCareerDto> humanCareerDto;

    public void addHumanCareerDto(List<HumanCareerDto> e){
        this.humanCareerDto = e;
    }
    public void addHumanEducationDto(List<HumanEducationDto> e){
        this.humanEducationDto = e;
    }
    public void addHumanResumeAttachmentDto(List<HumanResumeAttachmentDto> e){
        this.humanResumeAttachmentDto = e;
    }

}
