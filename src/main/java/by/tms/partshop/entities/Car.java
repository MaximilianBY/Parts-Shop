package by.tms.partshop.entities;

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
@Table(name = "CARS")
public class Car extends BaseEntity {

  @Column(name = "CAR_INDEX", nullable = false)
  private String carIndex;
  @Column(name = "BRAND", nullable = false)
  private String brand;
  @Column(name = "MODEL", nullable = false)
  private String model;
  @Column(name = "BODY", nullable = false)
  private String body;
  @Column(name = "TRANSMISSION")
  private String transmission;
  @Column(name = "ENGINE_ID")
  private String engineId;
  @Column(name = "TYPE_FUEL")
  private String typeFuel;
  @Column(name = "ENGINE_CC")
  private int engineCC;
  @Column(name = "ENGINE_FEATURES")
  private String engineFeatures;
  @Column(name = "VIN")
  private String vin;
  @Column(name = "YEAR")
  private short year;
  @Column(name = "COLOR")
  private String color;
  @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<Images> images;
}
