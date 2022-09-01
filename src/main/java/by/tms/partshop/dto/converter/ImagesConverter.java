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

  public Images imagesCarFromDto(ImagesDto imagesDto) {
    return Images.builder()
        .car(carRepository.getByCarIndex(imagesDto.getCarIndex()))
        .imagePath(imagesDto.getImagePath())
        .build();
  }

  public ImagesDto imagesCarToDto(Images images) {
    return ImagesDto.builder()
        .carId(images.getCar().getId())
        .carIndex(images.getCar().getCarIndex())
        .imagePath(images.getImagePath())
        .build();
  }

  public Images imagesPartFromDto(ImagesDto imagesDto) {
    return Images.builder()
        .part(partRepository.getByPartIndex(imagesDto.getPartIndex()))
        .imagePath(imagesDto.getImagePath())
        .build();
  }

  public ImagesDto imagesPartToDto(Images images) {
    return ImagesDto.builder()
        .partIndex(images.getPart().getPartIndex())
        .imagePath(images.getImagePath())
        .build();
  }
}
