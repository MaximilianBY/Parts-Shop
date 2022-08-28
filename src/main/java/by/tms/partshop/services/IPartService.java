package by.tms.partshop.services;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface IPartService {

  ModelAndView saveParts(MultipartFile file) throws Exception;
}
