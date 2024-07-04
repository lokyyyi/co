package com.co.hr.staff.repository;

import com.co.hr.staff.entity.HumanEducation;
import com.co.hr.staff.entity.HumanResumeAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HumanResumeAttachmentRepository extends JpaRepository<HumanResumeAttachment, Long> {
    @Query("SELECT r FROM HumanResumeAttachment r WHERE r.humanResume.id = :resumeId")
    List<HumanResumeAttachment> findAllWithoutDetailsById(@Param("resumeId") Long resumeId);
}
