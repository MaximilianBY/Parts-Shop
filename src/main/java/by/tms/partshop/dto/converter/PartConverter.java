package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.PartDto;
import by.tms.partshop.entities.Part;
import by.tms.partshop.repositories.CarRepository;
import by.tms.partshop.repositories.PartTypeAdditionalRepository;
import by.tms.partshop.repositories.PartTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PartConverter {

  private final CarRepository carRepository;
  private final PartTypeRepository partTypeRepository;
  private final PartTypeAdditionalRepository additionalRepository;

  public Part fromDto(PartDto partDto) {
    return Part.builder()
        .partIndex(partDto.getPartIndex())
        .car(carRepository.getByCarIdx(partDto.getCarIndex()))
        .partType(partTypeRepository.getByPartType(partDto.getPartType()))
        .additional(additionalRepository.getByTypeDescription(partDto.getAdditional()))
        .constructionNumber(partDto.getConstructionNumber())
        .description(partDto.getDescription())
        .price(partDto.getPrice())
        .availableToBuy(partDto.isAvailableToBuy())
        .build();
  }

  public PartDto toDto(Part part) {
    return PartDto.builder()
        .partIndex(part.getPartIndex())
        .carIndex(part.getCar().getCarIdx())
        .partType(part.getPartType().getPartType())
        .additional(part.getAdditional().getTypeDescription())
        .constructionNumber(part.getConstructionNumber())
        .description(part.getDescription())
        .price(part.getPrice())
        .build();
  }
}
