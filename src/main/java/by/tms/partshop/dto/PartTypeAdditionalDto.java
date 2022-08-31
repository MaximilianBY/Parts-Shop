package by.tms.partshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartTypeAdditionalDto {

//  private long partTypeId;
  private String partType;
  private String typeDescription;
}
