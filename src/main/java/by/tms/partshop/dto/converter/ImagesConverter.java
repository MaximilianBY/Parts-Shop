package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.ImagesDto;
import by.tms.partshop.entities.Images;
import by.tms.partshop.repositories.CarRepository;
import org.springframework.stereotype.Component;

@Component
public class ImagesConverter {

  private CarRepository carRepository;

  public ImagesConverter(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public Images fromDto(ImagesDto imagesDto) {
    return Images.builder()
        .car(carRepository.findById(imagesDto.getCarId()))
        .imagePath(imagesDto.getImagePath())
        .build();
  }
}
