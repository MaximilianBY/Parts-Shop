package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.HOME_PAGE;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {
  @GetMapping()
  public ModelAndView openHomePage() throws Exception {
    return new ModelAndView(HOME_PAGE);
  }
}
