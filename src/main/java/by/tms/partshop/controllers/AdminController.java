package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.entities.Car;
import by.tms.partshop.repositories.CarRepository;
import by.tms.partshop.services.ICarService;
import by.tms.partshop.services.IPartAdditionalService;
import by.tms.partshop.services.IPartService;
import by.tms.partshop.services.IPartTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
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
  private final IPartTypeService iPartTypeService;
  private final IPartAdditionalService additionalService;
  private final CarRepository carRepository;

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
  public ModelAndView uploadParts(@RequestParam("file") MultipartFile file) throws Exception {
    return iPartService.saveParts(file);
  }

  @PostMapping("/upload/partType")
  public ModelAndView uploadPartTypes(@RequestParam("file") MultipartFile file) throws Exception {
    return iPartTypeService.savePartType(file);
  }

  @PostMapping("/upload/additionalInfo")
  public ModelAndView uploadAdditionalInfo(@RequestParam("file") MultipartFile file)
      throws Exception {
    return additionalService.saveAdditionalInfo(file);
  }
  @PostMapping("/print/car")
  public ModelAndView printCar(@RequestParam("text") String input){
    ModelMap modelMap = new ModelMap();
    Car car = carRepository.getByCarIndex(input);
    modelMap.addAttribute("outputmess", car.toString());
    return new ModelAndView(ADMIN_PAGE, modelMap);
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
