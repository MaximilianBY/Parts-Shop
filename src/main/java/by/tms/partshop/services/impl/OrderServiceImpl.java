package by.tms.partshop.services.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.MY_PAGE;

import by.tms.partshop.dto.PartDto;
import by.tms.partshop.dto.converter.PartConverter;
import by.tms.partshop.dto.converter.UserConverter;
import by.tms.partshop.entities.Cart;
import by.tms.partshop.entities.Order;
import by.tms.partshop.entities.OrderDetails;
import by.tms.partshop.entities.OrderDetailsId;
import by.tms.partshop.repositories.OrderDetailsRepository;
import by.tms.partshop.repositories.OrderRepository;
import by.tms.partshop.repositories.PartRepository;
import by.tms.partshop.repositories.UserRepository;
import by.tms.partshop.services.IOrderService;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService {

  private final OrderRepository orderRepository;
  private final OrderDetailsRepository orderDetailsRepository;
  private final UserRepository userRepository;
  private final UserConverter userConverter;
  private final PartRepository partRepository;
  private final PartConverter partConverter;

  @Override
  public ModelAndView getUserOrders(long userId) {
    ModelMap modelMap = new ModelMap();
    modelMap.addAttribute("orders", orderRepository.getAllByUser_Id(userId));
    return new ModelAndView(MY_PAGE, modelMap);
  }

  @Override
  public int getOrderTotalPrice(List<Order> sumPriceOfProducts) {
    return 0;
  }

  @Override
  public void createOrder(long userId, Cart cart) throws Exception {
    Order order = Order.builder()
        .user(userConverter.userDataFromDto(userRepository.getUserById(userId)))
        .orderDate(LocalDate.now())
        .orderPrice(cart.getUserCartTotalPrice())
        .build();
    orderRepository.save(order);
    for (PartDto part : cart.getCart()) {
      part.setAvailableToBuy(false);
      partRepository.save(partConverter.fromDto(part));

      OrderDetailsId orderDetailsId = OrderDetailsId.builder()
          .order(order)
          .part(partConverter.fromDto(part))
          .build();

      OrderDetails orderDetails = OrderDetails.builder()
          .orderDetailsId(orderDetailsId)
          .build();

      orderDetailsRepository.save(orderDetails);
    }
    cart.clear();
  }
}
