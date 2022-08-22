package by.tms.partshop.services.impl;

import by.tms.partshop.dto.UserLoginDto;
import by.tms.partshop.repositories.UserRepository;
import by.tms.partshop.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public ModelAndView authenticate(UserLoginDto user) {
    if (userRepository.existsUserByLoginAndPassword(user.getLogin(), user.getPassword())) {
      log.info("login successful");
      return new ModelAndView();
    }
    log.info("no match");
    return null;
  }
}
