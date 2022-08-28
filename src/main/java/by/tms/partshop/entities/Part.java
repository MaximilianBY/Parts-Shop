package by.tms.partshop.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
  private long partIndex;
  @Column(name = "ADDITIONAL")
  private String additional;
  @Column(name = "CONSTRUCTION_NUMBER")
  private String constructionNumber;
  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "PRICE")
  private BigDecimal price;
  @Column(name = "IN_STOCK", nullable = false)
  private boolean isAvailableToBuy;
  @ManyToOne(optional = false)
  @JoinColumn(name = "ID", nullable = false, referencedColumnName = "ID")
  private PartType partType;
  @ManyToOne
  @JoinColumn(name = "CAR_IDX", nullable = false, referencedColumnName = "CAR_INDEX")
  private Car car;
  @OneToOne(mappedBy = "part", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  private Images images;
}