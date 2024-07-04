package com.co.hr.staff.entity;

import com.co.hr.staff.dto.HumanResumeDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class HumanResume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "human_resume_id", columnDefinition = "BIGINT")
    private long id;

    private String name;
    private String socialSecurityNumber;
    private String gender;
    private String email;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipcode;
    private String hopingSalary;

    @OneToMany(mappedBy = "humanResume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HumanEducation> humanEducation;
    public void addHumanEducation(HumanEducation humanEducation) {
        this.humanEducation.add(humanEducation);
    }

    private Date enterResumeDate;
    private String passOrNot;

    @OneToMany(mappedBy = "humanResume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HumanResumeAttachment> humanResumeAttachment;
    public void addHumanResumeAttachment(HumanResumeAttachment humanResumeAttachment) {
        this.humanResumeAttachment.add(humanResumeAttachment);
    }

    @OneToMany(mappedBy = "humanResume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HumanCareer> humanCareer;
    public void addHumanCareer(HumanCareer humanCareer) {
        this.humanCareer.add(humanCareer);
    }

    public void update(HumanResumeDto e){
        if(e.getName() != null) this.name=e.getName();
        if(e.getSocialSecurityNumber() != null) this.socialSecurityNumber=e.getSocialSecurityNumber();
        if(e.getGender() != null) this.gender=e.getGender();
        if(e.getEmail() != null) this.email=e.getEmail();
        if(e.getPhoneNumber() != null) this.phoneNumber=e.getPhoneNumber();
        if(e.getCity() != null) this.city=e.getCity();
        if(e.getStreet() != null) this.street=e.getStreet();
        if(e.getZipcode() != null) this.zipcode=e.getZipcode();
        if(e.getHopingSalary() != null) this.hopingSalary=e.getHopingSalary();
        if(e.getEnterResumeDate() != null) this.enterResumeDate=e.getEnterResumeDate();
        if(e.getPassOrNot() != null) this.passOrNot=e.getPassOrNot();
    }

}
