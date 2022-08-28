package by.tms.partshop.services.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.PartDto;
import by.tms.partshop.dto.converter.PartConverter;
import by.tms.partshop.entities.Part;
import by.tms.partshop.repositories.PartRepository;
import by.tms.partshop.services.IImageService;
import by.tms.partshop.services.IPartService;
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
public class PartServiceImpl implements IPartService {

  private PartRepository partRepository;
  private IImageService IImageService;
  private PartConverter partConverter;

  @Override
  public ModelAndView saveParts(MultipartFile file) throws Exception {
    ModelMap modelMap = new ModelMap();
    List<PartDto> csvCars = parseCsv(file);
    log.info(csvCars.toString());
    List<Part> cars = Optional.ofNullable(csvCars)
        .map(list -> list.stream()
            .map(partConverter::fromDto)
            .toList())
        .orElse(null);
    if (Optional.ofNullable(cars).isPresent()) {
      cars.forEach(partRepository::save);
      IImageService.saveImages(file);
      modelMap.addAttribute("categoryUploadMessage", HttpStatus.ACCEPTED);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    }
    modelMap.addAttribute("categoryUploadMessage", HttpStatus.NO_CONTENT);
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  private List<PartDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<PartDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(PartDto.class)
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