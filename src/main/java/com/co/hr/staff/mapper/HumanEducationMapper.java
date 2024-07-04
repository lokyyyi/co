package com.co.hr.staff.mapper;

import com.co.hr.staff.dto.HumanEducationDto;
import com.co.hr.staff.entity.HumanEducation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface HumanEducationMapper {
    HumanEducationMapper INSTANCE = Mappers.getMapper(HumanEducationMapper.class);

    @Mapping(target = "humanResume", expression = "java(null)")
    HumanEducationDto toDto(HumanEducation humanEducation);
    List<HumanEducationDto> toDtoList(List<HumanEducation> humanEducationList);
    HumanEducation toEntity(HumanEducationDto humanEducationDto);
}
