package com.co.hr.staff.repository;

import com.co.hr.staff.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByLoginId(String loginId);
}
