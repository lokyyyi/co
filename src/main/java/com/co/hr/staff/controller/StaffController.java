package com.co.hr.staff.controller;

import com.co.hr.common.dto.ResultDto;
import com.co.hr.staff.dto.StaffDto;
import com.co.hr.staff.repository.StaffRepository;
import com.co.hr.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<ResultDto<String>> join(@RequestBody StaffDto staffDto){
        return staffService.join(staffDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<ResultDto<String>> login(@RequestBody Map<String, String> staffDto){
        return staffService.login(staffDto);
    }

    //비밀번호 수정 전 비밀번호 검증 (이후에 메일보내는 로직 분리해서 재사용하기)
    @PostMapping("/validationForUpdatePassword")
    public ResponseEntity<ResultDto<String>> validationForUpdatePassword(@RequestParam String password){
        return staffService.validPassword(password);
    }

    @GetMapping("/check")
    public ResponseEntity<ResultDto<String>> checkVerificationCode(String verificationCode){
        return staffService.checkVerificationCode(verificationCode);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<ResultDto<String>> updatePassword(@RequestParam String password){
        return staffService.updatePassword(password);
    }

    @PostMapping("/updateStaff")
    public ResponseEntity<ResultDto<String>> updateStaff(@RequestBody StaffDto staffDto){
        return staffService.updateStaff(staffDto);
    }

    @PostMapping("/updateStaffDept")
    public ResponseEntity<ResultDto<String>> updateStaffDept(@RequestParam Integer deptId, @RequestParam Integer staffId){
        return staffService.updateStaffDept(deptId, staffId);
    }


}
