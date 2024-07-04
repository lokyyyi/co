package com.co.hr.staff.mapper;

import com.co.hr.staff.dto.HumanResumeAttachmentDto;
import com.co.hr.staff.entity.HumanResumeAttachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface HumanResumeAttachmentMapper {
    HumanResumeAttachmentMapper INSTANCE = Mappers.getMapper(HumanResumeAttachmentMapper.class);

    @Mapping(target = "humanResume", expression = "java(null)")
    HumanResumeAttachmentDto toDto(HumanResumeAttachment humanResumeAttachment);
    List<HumanResumeAttachmentDto> toDtoList(List<HumanResumeAttachment> HumanResumeAttachmentList);
    HumanResumeAttachment toEntity(HumanResumeAttachmentDto HumanResumeAttachmentDto);
}
