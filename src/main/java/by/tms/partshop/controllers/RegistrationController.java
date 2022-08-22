package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.REGISTRATION_PAGE;

import by.tms.partshop.dto.NewUserDto;
import by.tms.partshop.services.UserService;
import javax.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ModelAndView openRegistrationPage() {
    return new ModelAndView(REGISTRATION_PAGE);
  }

  @PostMapping
  public ModelAndView addNewUser(@ModelAttribute @Valid NewUserDto user, Errors errors)
      throws Exception {
    return userService.registration(user);
  }

  @ModelAttribute()
  public NewUserDto setUpUserForm() {
    return new NewUserDto();
  }
}
