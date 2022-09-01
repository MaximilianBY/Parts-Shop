package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.PartTypeDto;
import by.tms.partshop.entities.PartType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PartTypeConverter {

  public PartType fromDto(PartTypeDto typeDto) {
    return PartType.builder()
        .type(typeDto.getPartType())
        .build();
  }

  public PartTypeDto toDto(PartType type) {
    return PartTypeDto.builder()
        .id(type.getId())
        .partType(type.getType())
        .build();
  }
}
