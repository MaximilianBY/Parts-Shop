package by.tms.partshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

  @OneToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "CAR_ID", referencedColumnName = "ID")
  private Car car;
  @OneToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "PART_IDX", referencedColumnName = "PART_INDEX")
  private Part part;
  @Column(name = "IMAGE_PATHS", nullable = false)
  private String imagePath;
}
