package com.co.hr.staff.repository;

import com.co.hr.staff.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    void deleteByEmail(String email);
    Optional<VerificationCode> findByEmail(String email);
}
