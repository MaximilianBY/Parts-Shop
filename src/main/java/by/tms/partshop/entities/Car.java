package by.tms.partshop.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@Entity
@Table(name = "CARS")
public class Car extends BaseEntity{

  @Column(name = "CAR_INDEX", nullable = false)
  private String carIndex;
  @Column(name = "BRAND")
  private String brand;
  @Column(name = "MODEL")
  private String model;
  @Column(name = "BODY")
  private String body;
  @Column(name = "TRANSMISSION")
  private String transmission;
  @Column(name = "ENGINE_ID")
  private String engineId;
  @Column(name = "TYPE_FUEL")
  private String typeFuel;
  @Column(name = "ENGINE_CC")
  private String engineCC;
  @Column(name = "ENGINE_FEATURES")
  private String engineFeatures;
  @Column(name = "VIN")
  private String vin;
  @Column(name = "YEAR")
  private Integer year;
  @Column(name = "COLOR")
  private String color;
  @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<Part> parts;
  @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @ToString.Exclude
  private Images images;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Car car = (Car) o;
    return getId() != null && Objects.equals(getId(), car.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}