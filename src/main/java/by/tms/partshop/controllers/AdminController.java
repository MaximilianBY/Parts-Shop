package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.services.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/admins")
public class AdminController {

  private CarService carService;

  public AdminController(CarService carService) {
    this.carService = carService;
  }

  @GetMapping
  public ModelAndView openUploadPage() {
    return new ModelAndView(ADMIN_PAGE);
  }

  @PostMapping("/upload/cars")
  public ModelAndView uploadCategoriesFromFile(@RequestParam("file") MultipartFile file)
      throws Exception {
    return carService.saveCars(file);
  }

//  @PostMapping("/upload/product")
//  public ResponseEntity<Set<ProductDto>> uploadProductsFromFile(
//      @RequestParam("file") MultipartFile file) throws Exception {
//    return new ResponseEntity<>(productService.saveProductsFromFile(file), HttpStatus.CREATED);
//  }
//
//  @GetMapping(value = "/download/products.csv")
//  public ModelAndView downloadProductsCsv(HttpServletResponse response) throws Exception {
//    response.setHeader("Content-Disposition", "attachment; file=products.csv");
//    return productService.downloadProductsCSV(response.getWriter());
//  }
//
//  @GetMapping("/download/categories.csv")
//  public ModelAndView downloadCategoriesCsv(HttpServletResponse response) throws Exception {
//    response.setHeader("Content-Disposition", "attachment; file=categories.csv");
//    return categoryService.downloadCategoriesCSV(response.getWriter());
//  }
}
