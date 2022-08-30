package by.tms.partshop.services;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface IPartAdditionalService {
  ModelAndView saveAdditionalInfo(MultipartFile file) throws Exception;
}
