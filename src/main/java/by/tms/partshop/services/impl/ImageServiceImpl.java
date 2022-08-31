package by.tms.partshop.services.impl;

import by.tms.partshop.dto.ImagesDto;
import by.tms.partshop.dto.converter.ImagesConverter;
import by.tms.partshop.entities.Images;
import by.tms.partshop.repositories.ImagesRepository;
import by.tms.partshop.services.IImageService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@AllArgsConstructor
public class ImageServiceImpl implements IImageService {

  private ImagesRepository imagesRepository;

  private ImagesConverter imagesConverter;


  @Override
  public List<String> getAllCarImagePath(String carIndex) {
    return Arrays.stream(imagesRepository.getByCar_CarIdx(carIndex)
            .getImagePath()
            .split(";"))
        .toList();
  }

  @Override
  public List<String> getAllPartImagePath(long partIndex) {
    return Arrays.stream(imagesRepository.getByPart_PartIndex(partIndex)
            .getImagePath()
            .split(";"))
        .toList();
  }

  @Override
  public void saveImages(MultipartFile file) throws Exception {
    ModelMap modelMap = new ModelMap();
    List<ImagesDto> csvImages = parseCsv(file);
    log.info(csvImages.toString());
    List<Images> images = Optional.ofNullable(csvImages)
        .map(list -> list.stream()
            .map(imagesConverter::fromDto)
            .toList())
        .orElse(null);
    if (Optional.ofNullable(images).isPresent()) {
      images.forEach(imagesRepository::save);
    }
  }

  private List<ImagesDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<ImagesDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(ImagesDto.class)
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
