package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.PartTypeAdditionalDto;
import by.tms.partshop.entities.PartTypeAdditional;
import by.tms.partshop.repositories.PartTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PartTypeAdditionalConverter {

  private final PartTypeRepository typeRepository;

  public PartTypeAdditional fromDto(PartTypeAdditionalDto typeAdditionalDto) {
    return PartTypeAdditional.builder()
        .partType(typeRepository.getByType(typeAdditionalDto.getPartType()))
        .typeDescription(typeAdditionalDto.getTypeDescription())
        .build();
  }

  public PartTypeAdditionalDto toDto(PartTypeAdditional additional) {
    return PartTypeAdditionalDto.builder()
//        .partTypeId(additional.getPartType().getId())
        .partType(additional.getPartType().getType())
        .typeDescription(additional.getTypeDescription())
        .build();
  }
}
