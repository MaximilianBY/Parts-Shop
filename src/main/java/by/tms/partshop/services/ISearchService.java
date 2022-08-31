package by.tms.partshop.services;

import org.springframework.web.servlet.ModelAndView;

public interface ISearchService {

    ModelAndView getSearchResult(String searchQuery);
}
