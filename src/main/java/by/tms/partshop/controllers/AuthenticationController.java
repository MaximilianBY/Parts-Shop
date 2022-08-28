package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.LOGIN_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.LOGIN;
import static by.tms.partshop.util.constants.ShopConstants.PASSWORD;

import by.tms.partshop.dto.UserLoginDto;
import by.tms.partshop.services.IUserService;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class AuthenticationController {

  private IUserService IUserService;

  @GetMapping
  public ModelAndView openLoginPage() {
    return new ModelAndView(LOGIN_PAGE);
  }

  @PostMapping
  public ModelAndView login(@ModelAttribute @Valid UserLoginDto user, BindingResult bindingResult,
      ModelAndView modelAndView, HttpSession session) {
    if (bindingResult.hasErrors()) {
      populateError(LOGIN, modelAndView, bindingResult);
      populateError(PASSWORD, modelAndView, bindingResult);
      modelAndView.setViewName(LOGIN_PAGE);
      return modelAndView;
    }
    return IUserService.authenticate(user, session);
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
