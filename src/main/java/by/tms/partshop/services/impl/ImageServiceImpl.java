package by.tms.partshop.services.impl;

import by.tms.partshop.repositories.ImagesRepository;
import by.tms.partshop.services.ImageService;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

  private ImagesRepository imagesRepository;

  @Override
  public List<String> getAllImagePath(long id) {
    return Arrays.stream(imagesRepository.getByCar_IdOrPart_PartIndex(id)
            .getImagePath()
            .split(";"))
        .toList();
  }
}
