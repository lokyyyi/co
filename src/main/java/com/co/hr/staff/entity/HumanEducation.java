package com.co.hr.staff.entity;

import com.co.hr.staff.dto.HumanEducationDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class HumanEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "human_education_id", columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "human_resume_id")
    @JsonBackReference
    private HumanResume humanResume;

    private String educationType;
    private String educationName;
    private String admissionYear;
    private String graduationYear;

    public HumanEducation(HumanEducationDto e){
        if(e.getHumanResume() != null) this.setHumanResume(e.getHumanResume());
        if(e.getEducationType() != null) this.setEducationType(e.getEducationType());
        if(e.getEducationName() != null) this.setEducationName(e.getEducationName());
        if(e.getAdmissionYear() != null) this.setAdmissionYear(e.getAdmissionYear());
        if(e.getGraduationYear() != null) this.setGraduationYear(e.getGraduationYear());
    }
}


