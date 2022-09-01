package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.ShopConstants.PART_ID;

import by.tms.partshop.services.IPartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
@RequestMapping("/part")
public class PartController {

  private final IPartService partService;

  @GetMapping()
  public ModelAndView openProductPage(@RequestParam(PART_ID) String id) throws Exception {
    long partId = Long.parseLong(id);
    return partService.getPartData(partId);
  }
}
