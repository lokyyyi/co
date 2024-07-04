package com.co.hr.staff.service;

import com.co.hr.common.dto.ResultDto;
import com.co.hr.config.JwtTokenProvider;
import com.co.hr.department.entity.Department;
import com.co.hr.department.repository.DeptRepository;
import com.co.hr.exception.BadRequestException;
import com.co.hr.exception.ErrorCode;
import com.co.hr.staff.dto.StaffDto;
import com.co.hr.staff.entity.Staff;
import com.co.hr.staff.entity.VerificationCode;
import com.co.hr.staff.repository.StaffRepository;
import com.co.hr.staff.repository.VerificationCodeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationCodeRepository verificationCodeRepository;
    private final JavaMailSender mailSender;
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final DeptRepository deptRepository;

    @Transactional
    public ResponseEntity<ResultDto<String>> join(@RequestBody StaffDto staffDto){
        Long count = staffRepository.count();
        System.out.println(staffDto.getPassword());
        return ResponseEntity.ok(ResultDto.res(200, HttpStatus.OK.toString(),
                staffRepository.save(Staff.builder()
                        .loginId(staffDto.getLoginId())      //이후에 unique 속성 걸어야함
                        .password(passwordEncoder.encode(staffDto.getPassword()))
                        .name(staffDto.getName())
                        .email(staffDto.getEmail())
                        .address(staffDto.getAddress())
                        .roles(Collections.singletonList("ROLE_USER"))  //최초가입
                        .build()).getId() + "번의 id값으로," + (count + 1) + "번째 회원가입이 완료되었습니다."
        ));
    }


    public ResponseEntity<ResultDto<String>> login(Map<String, String> staffDto){
        Staff member = staffRepository.findByLoginId(staffDto.get("loginId"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다,"));
        if(!passwordEncoder.matches(staffDto.get("password"), member.getPassword())){
            throw new BadRequestException("잘못된 비밀번호 입니다.", ErrorCode.BAD_REQUEST);
        }
        return ResponseEntity.ok(ResultDto.res(200, HttpStatus.OK.toString(), jwtTokenProvider.createToken(member.getUsername(),member.getRoles())));

    }


    //비밀번호 검증 이후 등록된 메일로 인증 메일 발송
    public ResponseEntity<ResultDto<String>> validPassword(String password) {
        Staff staff = (Staff)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!passwordEncoder.matches(password, staff.getPassword())) {
            throw new BadRequestException("비밀번호가 잘못됬다용", ErrorCode.BAD_REQUEST);
        }
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(15);
        VerificationCode verificationCode = new VerificationCode(staff.getEmail(), expirationTime);
        verificationCodeRepository.save(verificationCode);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(staff.getEmail());
        message.setSubject("비밀번호 변경 코드 입니다.");
        message.setText("변경코드 : " + verificationCode.getCode());

        mailSender.send(message);

        return ResponseEntity.ok(ResultDto.res(200, HttpStatus.OK.toString(), "올바른 비밀번호입니다, 등록된 이메일 계정에서 인증메일을 확인하여 주십시오."));
    }


    public ResponseEntity<ResultDto<String>> checkVerificationCode(String verificationCode) {
        Staff staff = (Staff)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<VerificationCode> res = verificationCodeRepository.findByEmail(staff.getEmail());
//        log.info("--------------------------" + verificationCode);
//        log.info("--------------------------" + res.get().getCode());

        if(!res.get().getCode().equals(verificationCode)) {
            throw new BadRequestException("코드가 잘못됐어요", ErrorCode.BAD_REQUEST);
        }
        return ResponseEntity.ok(ResultDto.res(200, HttpStatus.OK.toString(), "올바른 코드를 입력했습니다."));
        
    }

    @Transactional
    public ResponseEntity<ResultDto<String>> updatePassword(String password) {
        Staff staff = (Staff)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Staff> member = staffRepository.findByLoginId(staff.getLoginId());

        try {
            staff = member.get();
            StaffDto staffDto = new StaffDto();
            staffDto.setPassword(passwordEncoder.encode(password));

            staff.update(staffDto);
        } catch (Exception e) {
            throw new BadRequestException("조졌음", ErrorCode.BAD_REQUEST);
        }
        return ResponseEntity.ok(ResultDto.res(200, HttpStatus.OK.toString(), "비번변경 성공"));

    }


    @Transactional
    public ResponseEntity<ResultDto<String>> updateStaff(StaffDto staffDto) {
        Staff staff = staffRepository.findByLoginId(staffDto.getLoginId()).orElseThrow(() ->
                new BadRequestException("유효하지 않은 loginId입니다.", ErrorCode.BAD_REQUEST)
        );

//        StaffDto temp;
//        try {
//            temp = objectMapper.convertValue(staffDto, StaffDto.class);
//        } catch (Exception e) {
//            log.error("StaffDto 변환 실패", e); // 로그 추가
//            throw new BadRequestException("변환 실패: 조졌어요", ErrorCode.BAD_REQUEST);
//        }

//        temp.setPassword(passwordEncoder.encode(staffDto.getPassword())); // 나중에 패스워드 변경이랑 호환해서 지우던가 하기

        staffDto.setPassword(passwordEncoder.encode(staffDto.getPassword())); // 나중에 패스워드 변경이랑 호환해서 지우던가 하기
        try {
            staff.update(staffDto);
        } catch (Exception e) {
            log.error("정보 변경 실패", e); // 로그 추가
            throw new BadRequestException("정보변경 실패", ErrorCode.BAD_REQUEST);
        }

        return ResponseEntity.ok(ResultDto.res(200, HttpStatus.OK.toString(), "정보수정 성공"));
    }

    //직원 부서 등록
    @Transactional
    public ResponseEntity<ResultDto<String>> updateStaffDept(Integer deptId, Integer staffId){
        try {
            Department department = deptRepository.findById(Long.valueOf(deptId)).get();
            Staff staff = staffRepository.findById(Long.valueOf(staffId)).get();
            StaffDto temp = new StaffDto();
            temp.setDepartment(department);
            staff.update(temp);
        } catch (Exception e) {
            throw new BadRequestException("유저의 부서 등록이 실패하였습니다.", ErrorCode.BAD_REQUEST);
        }
        return ResponseEntity.ok(ResultDto.res(200, HttpStatus.OK.toString(), "유저의 부서 등록이 성공하였습니다."));
    }
}
