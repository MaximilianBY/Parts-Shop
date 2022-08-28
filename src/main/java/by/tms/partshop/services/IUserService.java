package by.tms.partshop.services;

import by.tms.partshop.dto.NewUserDto;
import by.tms.partshop.dto.UserLoginDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface IUserService {
  ModelAndView authenticate(UserLoginDto user, HttpSession session);
  ModelAndView registration(NewUserDto userDto);

}
