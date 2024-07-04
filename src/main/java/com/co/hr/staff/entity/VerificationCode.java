package com.co.hr.staff.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String code;
    private LocalDateTime expirationTime;

    public VerificationCode(String email, LocalDateTime expirationTime) {
        this.email = email;
        this.expirationTime = expirationTime;
        this.code = UUID.randomUUID().toString().substring(0, 6);
    }
}
