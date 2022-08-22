package by.tms.partshop.services;

import by.tms.partshop.dto.NewUserDto;
import by.tms.partshop.dto.UserLoginDto;
import org.springframework.web.servlet.ModelAndView;

public interface UserService {
  ModelAndView authenticate(UserLoginDto user);
  ModelAndView registration(NewUserDto userDto);
}
