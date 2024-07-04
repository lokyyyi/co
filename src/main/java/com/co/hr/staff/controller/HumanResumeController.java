package com.co.hr.staff.controller;

import com.co.hr.common.dto.ResultDto;
import com.co.hr.staff.dto.HumanResumeDto;
import com.co.hr.staff.service.HumanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/humanresume")
public class HumanResumeController {

    private final HumanService humanService;

    //지원서등록
    @PostMapping("/submitResume")
    public ResponseEntity<ResultDto<String>> submitResume(@RequestBody HumanResumeDto humanResumeDto) {
        return humanService.submitResume(humanResumeDto);
    }

    //지원서 전체 조회
    @GetMapping("/selectAllResume")
    public ResponseEntity<ResultDto<List<HumanResumeDto>>> selectAllResume() {
        return humanService.selectAllResume();
    }

    //합/불합격 처리
    @PostMapping("/passOrNot")
    public ResponseEntity<ResultDto<String>> passOrNot(@RequestParam Integer id, @RequestParam String passOrNot) {
        return humanService.passOrNot(id, passOrNot);
    }

    //입사 처리
    @PostMapping("/joinToCompany")
    public ResponseEntity<ResultDto<String>> joinToCompany(@RequestParam Integer id) {
        return humanService.joinToCompany(id);
    }

    //퇴사 처리
    @PostMapping("/resignToCompany")
    public ResponseEntity<ResultDto<String>> resignToCompany(@RequestParam Integer id) {
        return humanService.resignToCompany(id);
    }


}
