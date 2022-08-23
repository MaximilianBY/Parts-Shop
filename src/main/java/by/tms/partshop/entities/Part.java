package by.tms.partshop.entities;

import java.math.BigDecimal;
import java.util.Set;
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
@Table(name = "Parts")
public class Part extends BaseEntity {

  @Column(name = "PART_INDEX", nullable = false)
  private int partIndex;
  @Column(name = "PART_TYPE", nullable = false)
  private String partType;
  @Column(name = "CONSTRUCTION_NUMBER")
  private String constructionNumber;
  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "PRICE")
  private BigDecimal price;
  @OneToMany(mappedBy = "part", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<Images> images;
}
