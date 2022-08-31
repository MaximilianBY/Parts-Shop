package by.tms.partshop.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "PARTS")
public class Part extends BaseEntity {

  @Column(name = "PART_INDEX", nullable = false)
  private long partIndex;
  @Column(name = "CONSTRUCTION_NUMBER")
  private String constructionNumber;
  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "PRICE")
  private int price;
  @Column(name = "IN_STOCK", nullable = false)
  private boolean availableToBuy;
  @ManyToOne(optional = false)
  @JoinColumn(name = "PART_TYPE", nullable = false, referencedColumnName = "ID")
  private PartType partType;

  @ManyToOne
  @JoinColumn(name = "ADDITIONAL_ID", nullable = false, referencedColumnName = "ID")
  private PartTypeAdditional additional;
  @ManyToOne(optional = false)
  @JoinColumn(name = "CAR_ID", nullable = false, referencedColumnName = "ID")
  private Car car;

  @OneToOne(mappedBy = "part", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  private Images images;
}