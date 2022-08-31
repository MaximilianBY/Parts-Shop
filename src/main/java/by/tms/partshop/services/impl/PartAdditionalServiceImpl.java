package by.tms.partshop.services.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.PartTypeAdditionalDto;
import by.tms.partshop.dto.converter.PartTypeAdditionalConverter;
import by.tms.partshop.entities.PartTypeAdditional;
import by.tms.partshop.repositories.PartTypeAdditionalRepository;
import by.tms.partshop.services.IPartAdditionalService;
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
public class PartAdditionalServiceImpl implements IPartAdditionalService {

  private final PartTypeAdditionalRepository additionalRepository;
  private final PartTypeAdditionalConverter additionalConverter;

  @Override
  public ModelAndView saveAdditionalInfo(MultipartFile file) throws Exception {
    ModelMap modelMap = new ModelMap();
    List<PartTypeAdditionalDto> csvFile = parseCsv(file);
    log.info(csvFile.toString());
    List<PartTypeAdditional> additionals = Optional.ofNullable(csvFile)
        .map(list -> list.stream()
            .map(additionalConverter::fromDto)
            .toList())
        .orElse(null);
    if (Optional.ofNullable(additionals).isPresent()) {
      log.info(additionals.get(0).getPartType().getId().toString());
      additionals.forEach(additionalRepository::save);
      modelMap.addAttribute("categoryUploadMessage", HttpStatus.ACCEPTED);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    }
    modelMap.addAttribute("categoryUploadMessage", HttpStatus.NO_CONTENT);
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  private List<PartTypeAdditionalDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<PartTypeAdditionalDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(PartTypeAdditionalDto.class)
            .withIgnoreLeadingWhiteSpace(false)
            .withIgnoreEmptyLine(false)
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
