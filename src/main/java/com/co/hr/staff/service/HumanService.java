package com.co.hr.staff.service;

import com.co.hr.common.dto.ResultDto;
import com.co.hr.exception.BadRequestException;
import com.co.hr.exception.ErrorCode;
import com.co.hr.staff.dto.*;
import com.co.hr.staff.entity.*;
import com.co.hr.staff.mapper.HumanCareerMapper;
import com.co.hr.staff.mapper.HumanEducationMapper;
import com.co.hr.staff.mapper.HumanResumeAttachmentMapper;
import com.co.hr.staff.mapper.HumanResumeMapper;
import com.co.hr.staff.mapper.HumanResumeMapper.*;
import com.co.hr.staff.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class HumanService {
    private final HumanResumeRepository humanResumeRepository;
    private final HumanResumeAttachmentRepository humanResumeAttachmentRepository;
    private final HumanEducationRepository humanEducationRepository;
    private final HumanCareerRepository humanCareerRepository;
    private final StaffRepository staffRepository;
    private final StaffService staffService;
    //private final ObjectMapper objectMapper;

    public ResponseEntity<ResultDto<String>> submitResume(HumanResumeDto humanResumeDto) {
        HumanResume humanResume = null;
        try {
            humanResume = humanResumeRepository.save(HumanResume.builder()
                    .name(humanResumeDto.getName())
                    .socialSecurityNumber(humanResumeDto.getSocialSecurityNumber())
                    .gender(humanResumeDto.getGender())
                    .email(humanResumeDto.getEmail())
                    .phoneNumber(humanResumeDto.getPhoneNumber())
                    .city(humanResumeDto.getCity())
                    .street(humanResumeDto.getStreet())
                    .zipcode(humanResumeDto.getZipcode())
                    .hopingSalary(humanResumeDto.getHopingSalary())
                    .enterResumeDate(new Date())
                    .passOrNot("H") //HOLD 보류
                    .build());
        } catch (Exception e) {
            throw new BadRequestException("이력서 등록 오류", ErrorCode.BAD_REQUEST);
        }

        try {
            //학위 등록
            for(HumanEducationDto educationDto : humanResumeDto.getHumanEducationDto()){
                educationDto.setHumanResume(humanResume);
                humanEducationRepository.save(new HumanEducation(educationDto));
            }
        } catch (Exception e) {
            throw new BadRequestException("학위 등록 오류", ErrorCode.BAD_REQUEST);
        }

        try {
            //경력 등록
            for(HumanCareerDto careerDto : humanResumeDto.getHumanCareerDto()){
                careerDto.setHumanResume(humanResume);
                humanCareerRepository.save(new HumanCareer(careerDto));
            }
        } catch (Exception e) {
            throw new BadRequestException("경력 등록 오류", ErrorCode.BAD_REQUEST);
        }

        //첨부파일등록

        return ResponseEntity.ok(ResultDto.res(200, humanResume.getName() + "님의 이력서가 등록되었습니다."));

    }

    public ResponseEntity<ResultDto<List<HumanResumeDto>>> selectAllResume() {

//        List<HumanResume> humanResumes = humanResumeRepository.findAll();

        List<HumanResume> humanResumes = humanResumeRepository.findAll();

        List<HumanResumeDto> dtos = HumanResumeMapper.INSTANCE.toDtoList(humanResumes);

        for (HumanResumeDto humanResumedto : dtos) {
           humanResumedto.addHumanCareerDto(addHumanCareer(humanResumedto));
           humanResumedto.addHumanEducationDto(addHumanEducation(humanResumedto));
           humanResumedto.addHumanResumeAttachmentDto(addHumanResumeAttachment(humanResumedto));
        }

        return ResponseEntity.ok(ResultDto.res(200, "이력서 전체 조회", dtos));

    }

    @Transactional
    public ResponseEntity<ResultDto<String>> passOrNot(Integer id, String passOrNot) {

        HumanResume humanResume = humanResumeRepository.findById(Long.valueOf(id)).get();
        HumanResumeDto dto = HumanResumeMapper.INSTANCE.toDto(humanResume);

        if(passOrNot.equals("P")){
            dto.setPassOrNot("P");
            humanResume.update(dto);

            return ResponseEntity.ok(ResultDto.res(200, id + "-" + dto.getName() + " 님 합격 처리 되었습니다."));
        }
            dto.setPassOrNot("N");
            humanResume.update(dto);

            return ResponseEntity.ok(ResultDto.res(200, id + "-" + dto.getName() + " 님 불합격 처리 되었습니다."));
    }


    public ResponseEntity<ResultDto<String>> joinToCompany(Integer id) {

        HumanResume humanResume = humanResumeRepository.findById(Long.valueOf(id)).get();
        HumanResumeDto dto = HumanResumeMapper.INSTANCE.toDto(humanResume);
        return staffService.join(toStaffDto(dto));
    }

    public ResponseEntity<ResultDto<String>> resignToCompany(Integer id) {

        Staff staff = staffRepository.findById(Long.valueOf(id)).get();
        staffRepository.delete(staff);

        return ResponseEntity.ok(ResultDto.res(200, id + "-" + staff.getName() + " 님 퇴사 처리 되었습니다."));
    }


    //해당 dto id값으로 humanCareerDto리스트를 뽑아옴
    public List<HumanCareerDto> addHumanCareer(HumanResumeDto humanResumeDto) {

        List<HumanCareer> hc = humanCareerRepository.findAllWithoutDetailsById(humanResumeDto.getId());

        return HumanCareerMapper.INSTANCE.toDtoList(hc);
    }
    //해당 dto id값으로 humanEducationDto리스트를 뽑아옴
    public List<HumanEducationDto> addHumanEducation(HumanResumeDto humanResumeDto) {

        List<HumanEducation> hc = humanEducationRepository.findAllWithoutDetailsById(humanResumeDto.getId());

        return HumanEducationMapper.INSTANCE.toDtoList(hc);
    }
    //해당 dto id값으로 humanResumeAttachmentDto리스트를 뽑아옴
    public List<HumanResumeAttachmentDto> addHumanResumeAttachment(HumanResumeDto humanResumeDto) {

        List<HumanResumeAttachment> hc = humanResumeAttachmentRepository.findAllWithoutDetailsById(humanResumeDto.getId());

        return HumanResumeAttachmentMapper.INSTANCE.toDtoList(hc);
    }

    public StaffDto toStaffDto(HumanResumeDto humanResumeDto) {
        //현재 직원으로 아이디 등록된 사람들의 숫자를 불러와서 "몇번쨰 co 직원이 되신것을 환영합니다" 출력하기
        Date dt = new Date();
        int year = (dt.getYear() + 1900) * 10000;
        Long count = staffRepository.count() + 1;
        System.out.println(humanResumeDto.getSocialSecurityNumber());
        return StaffDto.builder()
                .loginId(String.valueOf(year + count))
                .password(humanResumeDto.getSocialSecurityNumber())
                .name(humanResumeDto.getName())
                .email(humanResumeDto.getEmail())
                .address(new Address(humanResumeDto.getCity(), humanResumeDto.getStreet(), humanResumeDto.getZipcode()))
                .build();
    }
}
