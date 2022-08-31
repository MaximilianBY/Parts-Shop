package by.tms.partshop.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

  @ManyToOne(optional = false)
  @JoinColumn(name = "USER_ID", nullable = false, referencedColumnName = "id")
  private User user;
  @Column(name = "ORDER_DATE", nullable = false)
  private LocalDate orderDate;
  @Column(name = "ORDER_PRICE", nullable = false)
  private int orderPrice;
}
