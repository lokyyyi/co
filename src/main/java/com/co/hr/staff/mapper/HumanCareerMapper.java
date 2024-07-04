package com.co.hr.staff.mapper;

import com.co.hr.staff.dto.HumanCareerDto;
import com.co.hr.staff.entity.HumanCareer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface HumanCareerMapper {
    HumanCareerMapper INSTANCE = Mappers.getMapper(HumanCareerMapper.class);

    @Mapping(target = "humanResume", expression = "java(null)")
    HumanCareerDto toDto(HumanCareer humanCareer);

    List<HumanCareerDto> toDtoList(List<HumanCareer> HumanCareerList);
    HumanCareer toEntity(HumanCareerDto humanCareerDto);
}
