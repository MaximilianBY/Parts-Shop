package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.UserLoginDto;
import by.tms.partshop.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserLoginConverter {

  public User fromDto(UserLoginDto loginData) {
    return User.builder()
        .login(loginData.getLogin())
        .password(loginData.getPassword())
        .build();
  }
}
