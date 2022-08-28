package by.tms.partshop.services;

import by.tms.partshop.entities.Cart;
import by.tms.partshop.entities.Order;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;

public interface IOrderService {

  int getOrderTotalPrice(List<Order> sumPriceOfProducts);

  void createOrder(long userId, Cart cart) throws Exception;

  ModelAndView getUserOrders(long userId);
}
