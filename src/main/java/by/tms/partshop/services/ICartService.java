package by.tms.partshop.services;

import by.tms.partshop.entities.Cart;
import by.tms.partshop.exceptions.UnavailableForPurchaseException;
import org.springframework.web.servlet.ModelAndView;

public interface ICartService {

  ModelAndView getAllProductsFromCart(Cart cart);

  ModelAndView addPartToCart(long partIndex, Cart cart) throws UnavailableForPurchaseException;

  ModelAndView deletePartFromCart(long partIndex, Cart cart);

  ModelAndView clearUserCart(Cart cart);

  ModelAndView confirmOrder(long userId, Cart cart) throws Exception;
}
