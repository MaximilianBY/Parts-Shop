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
        .car(carRepository.getByCarIdx(imagesDto.getCarIndex()))
        .part(partRepository.getByPartIndex(imagesDto.getPartIndex()))
        .imagePath(imagesDto.getImagePath())
        .build();
  }

  public ImagesDto toDto(Images images) {
    return ImagesDto.builder()
        .carIndex(images.getCar().getCarIdx())
        .partIndex(images.getPart().getPartIndex())
        .imagePath(images.getImagePath())
        .build();
  }
}
