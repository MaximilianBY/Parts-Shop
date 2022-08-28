package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.PartDto;
import by.tms.partshop.entities.Part;
import by.tms.partshop.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PartConverter {

  private final CarRepository carRepository;

  public Part fromDto(PartDto partDto) {
    return Part.builder()
        .partIndex(partDto.getPartIndex())
        .car(carRepository.getByCarIndex(partDto.getCarIndex()))
        .partType(partDto.getPartType())
        .additional(partDto.getAdditional())
        .constructionNumber(partDto.getConstructionNumber())
        .description(partDto.getDescription())
        .price(partDto.getPrice())
        .build();
  }

  public PartDto toDto(Part part) {
    return PartDto.builder()
        .partIndex(part.getPartIndex())
        .carIndex(part.getCar().getCarIndex())
        .partType(part.getPartType())
        .additional(part.getAdditional())
        .constructionNumber(part.getConstructionNumber())
        .description(part.getDescription())
        .price(part.getPrice())
        .build();
  }
}
