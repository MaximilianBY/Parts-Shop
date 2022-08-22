package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.UserDataDto;
import by.tms.partshop.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataConverter {

  public UserDataDto toDto(User user){
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

  public User fromDto(UserDataDto user){
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
