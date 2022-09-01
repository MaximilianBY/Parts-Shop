package by.tms.partshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PartDto {

  private Long partIndex;
  private Long carId;
  private String carIndex;
  private String partType;
  private String additional;
  private String constructionNumber;
  private String description;
  private int price;
  private boolean availableToBuy;
}
