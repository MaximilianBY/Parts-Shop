package by.tms.partshop.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

  private String carIndex;
  private String brand;
  private String model;
  private String body;
  private String transmission;
  private String engineId;
  private String typeFuel;
  private String engineCC;
  private String engineFeatures;
  private Integer year;
  private String color;
  private String vin;
}
