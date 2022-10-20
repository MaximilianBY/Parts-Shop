package by.tms.partshop.services.basicServices;

import by.tms.partshop.dto.NewUserDto;
import org.springframework.web.servlet.ModelAndView;

public interface IUserService {

  ModelAndView registration(NewUserDto userDto);

  ModelAndView getUserData(long id);
}
