package com.co.hr.staff.repository;

import com.co.hr.staff.entity.HumanEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HumanEducationRepository extends JpaRepository<HumanEducation, Long> {
    @Query("SELECT r FROM HumanEducation r WHERE r.humanResume.id = :resumeId")
    List<HumanEducation> findAllWithoutDetailsById(@Param("resumeId") Long resumeId);
}
