package by.tms.partshop.services.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.LOGIN_PAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.REGISTRATION_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.USER_ID;

import by.tms.partshop.dto.NewUserDto;
import by.tms.partshop.dto.UserLoginDto;
import by.tms.partshop.dto.converter.UserConverter;
import by.tms.partshop.exceptions.AuthorizationException;
import by.tms.partshop.repositories.UserRepository;
import by.tms.partshop.services.IUserService;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

  private UserRepository userRepository;
  private UserConverter userConverter;

  public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
    this.userRepository = userRepository;
    this.userConverter = userConverter;
  }

  @Override
  public ModelAndView authenticate(UserLoginDto user, HttpSession session) {
    try {
      if (userRepository.existsUserByLoginAndPassword(user.getLogin(), user.getPassword())) {
        session.setAttribute(USER_ID, userRepository.getUserId(user.getLogin()));
        log.info("Login successful");
        return new ModelAndView();
      } throw new  AuthorizationException("User does not exist");
    } catch (AuthorizationException e) {
      log.info(e.getMessage());
      return new ModelAndView(LOGIN_PAGE, HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ModelAndView registration(NewUserDto userDto) {
    try {
      userRepository.save(userConverter.newUserFromDto(userDto));
    } catch (Exception e) {
      log.info(e.getMessage());
      return new ModelAndView(REGISTRATION_PAGE);
    }
    return new ModelAndView(LOGIN_PAGE);
  }
}
