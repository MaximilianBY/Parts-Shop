package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.services.ICarService;
import by.tms.partshop.services.IPartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admins")
public class AdminController {

  private final ICarService iCarService;
  private final IPartService iPartService;

  @GetMapping
  public ModelAndView openUploadPage() {
    return new ModelAndView(ADMIN_PAGE);
  }

  @PostMapping("/upload/cars")
  public ModelAndView uploadCars(@RequestParam("file") MultipartFile file)
      throws Exception {
    return iCarService.saveCars(file);
  }

  @PostMapping("/upload/parts")
  public ModelAndView uploadImages(@RequestParam("file") MultipartFile file) throws Exception {
    return iPartService.saveParts(file);
  }
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
