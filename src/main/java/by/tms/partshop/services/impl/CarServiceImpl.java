package by.tms.partshop.services.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.CarDto;
import by.tms.partshop.dto.converter.CarConverter;
import by.tms.partshop.entities.Car;
import by.tms.partshop.repositories.CarRepository;
import by.tms.partshop.services.ICarService;
import by.tms.partshop.services.IImageService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service
@AllArgsConstructor
public class CarServiceImpl implements ICarService {

  private CarConverter carConverter;
  private CarRepository carRepository;
  private IImageService IImageService;

  @Override
  public ModelAndView saveCars(MultipartFile file) throws Exception {
    ModelMap modelMap = new ModelMap();
    List<CarDto> csvCars = parseCsv(file);
    log.info(csvCars.toString());
    List<Car> cars = Optional.ofNullable(csvCars)
        .map(list -> list.stream()
            .map(carConverter::fromDto)
            .toList())
        .orElse(null);
    if (Optional.ofNullable(cars).isPresent()) {
      cars.forEach(carRepository::save);
      IImageService.saveCarImages(file);
      modelMap.addAttribute("categoryUploadMessage", HttpStatus.ACCEPTED);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    }
    modelMap.addAttribute("categoryUploadMessage", HttpStatus.NO_CONTENT);
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  private List<CarDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<CarDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(CarDto.class)
            .withIgnoreLeadingWhiteSpace(true)
            .withSeparator(',')
            .build();

        return csvToBean.parse();
      } catch (Exception ex) {
        log.error("Exception occurred during CSV parsing: {}", ex.getMessage());
      }
    } else {
      log.error("Empty CSV file is uploaded.");
    }
    return Collections.emptyList();
  }
}
