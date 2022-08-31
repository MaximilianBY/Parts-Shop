package by.tms.partshop.entities;

import by.tms.partshop.dto.PartDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class Cart {

  private List<PartDto> cart;

  public Cart() {
    this.cart = new ArrayList<>();
  }

  public void addPartToCart(PartDto addedPart) {
    cart.add(addedPart);
  }

  public int getUserCartTotalPrice() {
    if (Optional.ofNullable(cart).isPresent()) {
      return cart.stream().map(PartDto::getPrice).reduce(BigDecimal::add).get();
    }
    return 0;
  }

  public void delUnnecessaryPart(PartDto unnecessaryPart) {
    cart.removeIf(
        part -> part.getPartIndex() == unnecessaryPart.getPartIndex());
  }

  public void clear() {
    cart.clear();
  }
}
