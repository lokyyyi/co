package com.co.hr.staff.repository;

import com.co.hr.staff.entity.HumanResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HumanResumeRepository extends JpaRepository<HumanResume, Long> {
    @Query("SELECT r FROM HumanResume r")
    List<HumanResume> findAllWithoutDetails();

    @Query("SELECT DISTINCT t FROM HumanResume t LEFT JOIN FETCH t.humanEducation h LEFT JOIN FETCH t.humanResumeAttachment a LEFT JOIN FETCH t.humanCareer c")
    List<HumanResume> findAllDistinct();
}
