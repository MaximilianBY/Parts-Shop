package by.tms.partshop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {
//
//  private CategoryService categoryService;
//  private ProductService productService;
//
//  public HomeController(CategoryService categoryService, ProductService productService) {
//    this.categoryService = categoryService;
//    this.productService = productService;
//  }
//
//  @GetMapping()
//  public ModelAndView openHomePage(HttpSession httpSession) throws Exception {
//    return categoryService.openCategoryPage(httpSession);
//  }
//
//  @GetMapping("/all")
//  public ResponseEntity<Set<CategoryDto>> getAllCategories() {
//    return new ResponseEntity<>(categoryService.getAllCategoriesDto(), HttpStatus.OK);
//  }
//
//  @GetMapping("/devices/{id}")
//  public ModelAndView openDevicesPage(@PathVariable String id) throws Exception {
//    log.info("category id is: " + id);
//    int categoryId = Integer.parseInt(id);
//    return productService.openDevicesPage(categoryId);
//  }
}
