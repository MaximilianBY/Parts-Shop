package by.tms.partshop.services;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {

  List<String> getAllCarImagePath(String carIndex);

  List<String> getAllPartImagePath(long partIndex);

  void saveCarImages(MultipartFile file) throws Exception;
  void savePartImages(MultipartFile file) throws Exception;
}
