package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.LOGIN_PAGE;
import static by.tms.partshop.util.constants.entityConstants.UserConstants.LOGIN;
import static by.tms.partshop.util.constants.entityConstants.UserConstants.PASSWORD;

import by.tms.partshop.dto.UserLoginDto;
import by.tms.partshop.exceptions.AuthorizationException;
import by.tms.partshop.services.UserService;
import java.util.Objects;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/login")
public class AuthenticationController {

  private UserService userService;

  public AuthenticationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ModelAndView openLoginPage() {
    return new ModelAndView(LOGIN_PAGE);
  }

  @PostMapping
  public ModelAndView login(@ModelAttribute @Valid UserLoginDto user, BindingResult bindingResult,
      ModelAndView modelAndView) throws AuthorizationException {
    if (bindingResult.hasErrors()) {
      log.info("inside check errors");
      populateError("login", modelAndView, bindingResult);
      populateError("password", modelAndView, bindingResult);
      modelAndView.setViewName(LOGIN_PAGE);
      return modelAndView;
    }
    return userService.authenticate(user);
  }

  private void populateError(String field, ModelAndView modelAndView, BindingResult bindingResult) {
    if (bindingResult.hasFieldErrors(field)) {
      modelAndView.addObject(field + "Error",
          Objects.requireNonNull(bindingResult.getFieldError(field))
              .getDefaultMessage());
    }
  }

  @ModelAttribute
  public UserLoginDto setUpUserForm() {
    return new UserLoginDto();
  }
}
