package by.tms.partshop.dto;

import java.time.LocalDate;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;
  private String login;
  private String password;
  private String name;
  private String surname;
  private LocalDate birthday;
  private String email;
  private String phoneNumber;
}
