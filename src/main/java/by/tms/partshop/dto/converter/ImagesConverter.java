package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.ImagesDto;
import by.tms.partshop.entities.Images;
import by.tms.partshop.repositories.CarRepository;
import by.tms.partshop.repositories.PartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ImagesConverter {

  private CarRepository carRepository;
  private PartRepository partRepository;

  public Images fromDto(ImagesDto imagesDto) {
    return Images.builder()
        .car(carRepository.getByCarIndex(imagesDto.getCarIndex()))
        .part(partRepository.getByPartIndex(imagesDto.getPartIndex()))
        .imagePath(imagesDto.getImagePath())
        .build();
  }
}
