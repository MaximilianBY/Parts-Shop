package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.CarDto;
import by.tms.partshop.entities.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

  public Car fromDto(CarDto carDto) {
    return Car.builder()
        .carIndex(carDto.getCarIndex())
        .brand(carDto.getBrand())
        .model(carDto.getModel())
        .year(carDto.getYear())
        .body(carDto.getBody())
        .transmission(carDto.getTransmission())
        .color(carDto.getColor())
        .engineId(carDto.getEngineId())
        .typeFuel(carDto.getTypeFuel())
        .engineCC(carDto.getEngineCC())
        .engineFeatures(carDto.getEngineFeatures())
        .vin(carDto.getVin())
        .build();
  }
}
