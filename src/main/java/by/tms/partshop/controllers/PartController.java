package by.tms.partshop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
