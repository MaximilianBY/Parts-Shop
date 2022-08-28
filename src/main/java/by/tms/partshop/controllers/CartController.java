package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.LOGIN_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.PART_INDEX;
import static by.tms.partshop.util.constants.ShopConstants.SHOPPING_CART;
import static by.tms.partshop.util.constants.ShopConstants.USER_ID;

import by.tms.partshop.entities.Cart;
import by.tms.partshop.services.ICartService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@AllArgsConstructor
@SessionAttributes({SHOPPING_CART})
@RequestMapping("/cart")
public class CartController {

  private final ICartService cartService;

  @GetMapping("/open")
  public ModelAndView openCartPage(@ModelAttribute(SHOPPING_CART) Cart cart) {
    return cartService.getAllProductsFromCart(cart);
  }

  @GetMapping("/add")
  public ModelAndView addProductToCart(@RequestParam(PART_INDEX) String id,
      @ModelAttribute(SHOPPING_CART) Cart cart) throws Exception {
    long partIndex = Long.parseLong(id);
    return cartService.addPartToCart(partIndex, cart);
  }

  @GetMapping("/delete")
  public ModelAndView delProductFromCart(@RequestParam(PART_INDEX) String id,
      @SessionAttribute(SHOPPING_CART) Cart cart) throws Exception {
    long partIndex = Long.parseLong(id);
    return cartService.deletePartFromCart(partIndex, cart);
  }

  @GetMapping("/reset")
  public ModelAndView clearUserCart(@SessionAttribute(SHOPPING_CART) Cart cart) {
    return cartService.clearUserCart(cart);
  }

  @GetMapping("/confirmOrder")
  public ModelAndView confirmOrder(@SessionAttribute(SHOPPING_CART) Cart cart, HttpSession session)
      throws Exception {
    if (Optional.ofNullable(session.getAttribute(USER_ID)).isEmpty()) {
      log.info("Inside abort confirm order");
      return new ModelAndView(LOGIN_PAGE);
    }
    return cartService.confirmOrder((long) session.getAttribute(USER_ID), cart);
  }

  @ModelAttribute(SHOPPING_CART)
  public Cart shoppingCart() {
    return new Cart();
  }
}
