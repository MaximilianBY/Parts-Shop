package by.tms.partshop.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class PartTypeAdditional extends BaseEntity {

  @ManyToOne(optional = false)
  @JoinColumn(name = "PART_TYPE_ID", nullable = false, referencedColumnName = "ID")
  private PartType partType;
  @Column(name = "DESCRIPTION")
  private String typeDescription;
  @OneToMany
  private List<Part> part;
}
