package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.NewUserDto;
import by.tms.partshop.dto.UserDataDto;
import by.tms.partshop.dto.UserLoginDto;
import by.tms.partshop.entities.User;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

  public User loginFromDto(UserLoginDto loginData) {
    return User.builder()
        .login(loginData.getLogin())
        .password(loginData.getPassword())
        .build();
  }
  public User newUserFromDto(NewUserDto newUser){
    return User.builder()
        .login(newUser.getLogin())
        .password(newUser.getPassword())
        .name(newUser.getName())
        .surname(newUser.getSurname())
        .birthday(LocalDate.parse(newUser.getBirthday()))
        .email(newUser.getEmail())
        .phoneNumber(newUser.getPhoneNumber())
        .build();
  }
  public UserDataDto userDataToDto(User user){
    return UserDataDto.builder()
        .id(user.getId())
        .name(user.getName())
        .surname(user.getSurname())
        .birthday(user.getBirthday())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .balance(user.getBalance())
        .build();
  }

  public User userDataFromDto(UserDataDto user){
    return User.builder()
        .id(user.getId())
        .name(user.getName())
        .surname(user.getSurname())
        .birthday(user.getBirthday())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .balance(user.getBalance())
        .build();
  }
}
