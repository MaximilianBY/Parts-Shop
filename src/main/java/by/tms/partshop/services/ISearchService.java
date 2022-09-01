package by.tms.partshop.services;

import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface ISearchService {

    ModelAndView getSearchResult(HttpSession session);
}
