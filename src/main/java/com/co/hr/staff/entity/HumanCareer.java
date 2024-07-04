package com.co.hr.staff.entity;

import com.co.hr.staff.dto.HumanCareerDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class HumanCareer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "human_career_id", columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "human_resume_id")
    @JsonBackReference
    private HumanResume humanResume;

    private String companyName;
    private String joinDate;
    private String resignDate;
    private String career;

    public HumanCareer(HumanCareerDto e){
        if(e.getHumanResume() != null) this.setHumanResume(e.getHumanResume());
        if(e.getCompanyName() != null) this.setCompanyName(e.getCompanyName());
        if(e.getJoinDate() != null) this.setJoinDate(e.getJoinDate());
        if(e.getResignDate() != null) this.setResignDate(e.getResignDate());
        if(e.getCareer() != null) this.setCareer(e.getCareer());
    }
}
