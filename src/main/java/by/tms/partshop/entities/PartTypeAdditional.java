package by.tms.partshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Entity
@Table(name = "PART_TYPE_ADDITIONAL")
public class PartTypeAdditional extends BaseEntity{
  @Column(name = "DESCRIPTION")
  private String typeDescription;
  @ManyToOne(optional = false)
  private PartType partType;
}
