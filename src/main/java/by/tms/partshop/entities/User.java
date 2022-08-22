package by.tms.partshop.entities;

import by.tms.partshop.util.EmailConstraint;
import by.tms.partshop.util.PasswordConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "USERS")
public class User extends BaseEntity {

  @Column(name = "LOGIN", nullable = false)
  private String login;
  @Column(name = "PASSWORD", nullable = false)
  @PasswordConstraint
  private String password;
  @Column(name = "NAME", nullable = false)
  private String name;
  @Column(name = "SURNAME", nullable = false)
  private String surname;
  @Column(name = "BIRTHDAY", nullable = false)
  private LocalDate birthday;
  @Column(name = "EMAIL", nullable = false)
  @EmailConstraint
  private String email;
  @Column(name = "PHONE_NUMBER", nullable = false)
  private String phoneNumber;
  @Column(name = "BALANCE", nullable = false)
  private BigDecimal balance;
}
