package by.tms.partshop.services.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.PartTypeAdditionalDto;
import by.tms.partshop.dto.PartTypeDto;
import by.tms.partshop.dto.converter.PartTypeConverter;
import by.tms.partshop.entities.PartType;
import by.tms.partshop.entities.PartTypeAdditional;
import by.tms.partshop.repositories.PartTypeRepository;
import by.tms.partshop.services.IPartTypeService;
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
@AllArgsConstructor
@Service
public class PartTypeServiceImpl implements IPartTypeService {

  private final PartTypeRepository partTypeRepository;
  private final PartTypeConverter typeConverter;

  @Override
  public ModelAndView savePartType(MultipartFile file) throws Exception {
    ModelMap modelMap = new ModelMap();
    List<PartTypeDto> csvFile = parseCsv(file);
    log.info(csvFile.toString());
    List<PartType> partTypes = Optional.ofNullable(csvFile)
        .map(list -> list.stream()
            .map(typeConverter::fromDto)
            .toList())
        .orElse(null);
    if (Optional.ofNullable(csvFile).isPresent()) {
      partTypes.forEach(partTypeRepository::save);
      modelMap.addAttribute("categoryUploadMessage", HttpStatus.ACCEPTED);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    }
    modelMap.addAttribute("categoryUploadMessage", HttpStatus.NO_CONTENT);
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  private List<PartTypeDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<PartTypeDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(PartTypeDto.class)
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
