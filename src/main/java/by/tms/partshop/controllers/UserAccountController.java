package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.LOGIN_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.USER_ID;

import by.tms.partshop.services.IOrderService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/mypage")
public class UserAccountController {

  private final IOrderService orderService;

  @GetMapping
  public ModelAndView openAccountPage(HttpSession session) throws Exception {
    if (Optional.ofNullable(session.getAttribute(USER_ID)).isEmpty()) {
      log.info("inside abort confirm order");
      return new ModelAndView(LOGIN_PAGE);
    }
    return orderService.getUserOrders((long) session.getAttribute(USER_ID));
  }
}