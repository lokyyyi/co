package com.co.hr.staff.mapper;

import com.co.hr.staff.dto.HumanResumeDto;
import com.co.hr.staff.entity.HumanResume;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface HumanResumeMapper {
    HumanResumeMapper INSTANCE = Mappers.getMapper(HumanResumeMapper.class);

//    @Mapping(target = "humanEducationDto", source = "humanEducation")
//    @Mapping(target = "humanResumeAttachmentDto", source = "humanResumeAttachment")
//    @Mapping(target = "humanCareerDto", source = "humanCareer")
    @Mapping(target = "humanEducationDto", expression = "java(null)")
    @Mapping(target = "humanResumeAttachmentDto", expression = "java(null)")
    @Mapping(target = "humanCareerDto", expression = "java(null)")
    HumanResumeDto toDto(HumanResume humanResume);

    List<HumanResumeDto> toDtoList(List<HumanResume> humanResumeList);

    @Mapping(target = "humanEducation", source = "humanEducationDto")
    @Mapping(target = "humanResumeAttachment", source = "humanResumeAttachmentDto")
    @Mapping(target = "humanCareer", source = "humanCareerDto")
    HumanResume toEntity(HumanResumeDto humanResumeDto);
}
