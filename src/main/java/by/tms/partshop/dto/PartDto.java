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
public class PartDto {

  private long partIndex;
  private long partTypeId;
  private String partType;
  private long additionalId;
  private String additional;
  private String constructionNumber;
  private String description;
  private BigDecimal price;
  private boolean isAvailableToBuy;
  private String carIndex;
}
