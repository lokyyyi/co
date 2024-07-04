package com.co.hr.staff.repository;

import com.co.hr.staff.entity.HumanCareer;
import com.co.hr.staff.entity.HumanResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HumanCareerRepository extends JpaRepository<HumanCareer, Long> {
    @Query("SELECT r FROM HumanCareer r WHERE r.humanResume.id = :resumeId")
    List<HumanCareer> findAllWithoutDetailsById(@Param("resumeId") Long resumeId);
}
