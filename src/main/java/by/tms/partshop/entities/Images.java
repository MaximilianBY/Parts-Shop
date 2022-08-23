package by.tms.partshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "Images")
public class Images extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "CAR_INDEX")
  private Car car;
  @ManyToOne
  @JoinColumn(name = "PART_INDEX")
  private Part part;
  @Column(name = "IMAGE_PATHS", nullable = false)
  private String imagePath;
}
