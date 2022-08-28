package by.tms.partshop.services.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.CART_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.SHOPPING_CART;
import static by.tms.partshop.util.constants.ShopConstants.TOTAL_PRICE;

import by.tms.partshop.dto.PartDto;
import by.tms.partshop.dto.converter.PartConverter;
import by.tms.partshop.entities.Cart;
import by.tms.partshop.repositories.PartRepository;
import by.tms.partshop.services.ICartService;
import by.tms.partshop.services.IOrderService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Service
public class CartServiceImpl implements ICartService {

  private final PartRepository partRepository;
  private final PartConverter partConverter;
  private final IOrderService orderService;

  @Override
  public ModelAndView getAllProductsFromCart(Cart cart) {
    ModelMap modelMap = new ModelMap();
    if (Optional.ofNullable(cart).isPresent()) {
      modelMap.addAttribute(SHOPPING_CART, cart.getCart());
    }
    return new ModelAndView(CART_PAGE, modelMap);
  }

  @Override
  public ModelAndView addPartToCart(long partIndex, Cart cart) {
    ModelMap modelMap = new ModelMap();
    if (partRepository.findByPartIndexAndAvailableToBuyIsTrue(partIndex)) {
      PartDto partDto = partConverter.toDto(partRepository.getByPartIndex(partIndex));
      cart.addPartToCart(partDto);
    }
    modelMap.addAttribute(SHOPPING_CART, cart.getCart());
    modelMap.addAttribute(TOTAL_PRICE, cart.getUserCartTotalPrice());
    return new ModelAndView(CART_PAGE, modelMap);
  }

  @Override
  public ModelAndView deletePartFromCart(long partIndex, Cart cart) {
    ModelMap modelMap = new ModelMap();

    PartDto partDto = partConverter.toDto(partRepository.getByPartIndex(partIndex));
    cart.delUnnecessaryPart(partDto);

    modelMap.addAttribute(SHOPPING_CART, cart.getCart());
    modelMap.addAttribute(TOTAL_PRICE, cart.getUserCartTotalPrice());
    return new ModelAndView(CART_PAGE, modelMap);
  }

  @Override
  public ModelAndView clearUserCart(Cart cart) {
    ModelMap modelMap = new ModelMap();
    cart.clear();
    modelMap.addAttribute(SHOPPING_CART, cart.getCart());
    modelMap.addAttribute(TOTAL_PRICE, cart.getUserCartTotalPrice());
    return new ModelAndView(CART_PAGE, modelMap);
  }

  @Override
  public ModelAndView confirmOrder(long userId, Cart cart) throws Exception {
    ModelMap modelMap = new ModelMap();
    orderService.createOrder(userId, cart);
    modelMap.addAttribute(SHOPPING_CART, cart.getCart());
    modelMap.addAttribute(TOTAL_PRICE, cart.getUserCartTotalPrice());
    return new ModelAndView(CART_PAGE, modelMap);
  }

}
