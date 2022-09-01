package by.tms.partshop.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "PART_TYPE")
public class PartType extends BaseEntity {

  @Column(name = "TYPE", nullable = false)
  private String type;
  @OneToMany(mappedBy = "partType", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  private List<Part> part;
  @OneToMany(mappedBy = "partType", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  private List<PartTypeAdditional> additional;
}
