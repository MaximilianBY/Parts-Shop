package by.tms.partshop.services.impl;

import by.tms.partshop.entities.Part;
import by.tms.partshop.repositories.PartRepository;
import by.tms.partshop.services.ISearchService;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@AllArgsConstructor
@Service
public class SearchServiceImpl implements ISearchService {

  private PartRepository partRepository;

  @Override
    public ModelAndView getSearchResult(HttpSession session) {
        String searchQuery = (String) session.getAttribute("searchQuery");
        ModelMap modelMap = new ModelMap();
        List<Part> allProducts = partRepository.findAll();
        List<Part> searchResult = new ArrayList<>(searchProducts(allProducts, searchQuery));
//        modelMap.addAttribute(PRODUCTS_FROM_SEARCH, searchResult);
//        return new ModelAndView(SEARCH_PAGE.getPath(), modelMap);
    return new ModelAndView();
    }

    private Set<Part> searchProducts(List<Part> allProducts, String query) {
        Set<Part> products = new HashSet<>();
        products.addAll(searchByPartType(allProducts, query));
        products.addAll(searchByDescription(allProducts, query));
        products.addAll(searchByDescription(allProducts, query));


        return products;
    }

    private List<Part> searchByPartType(List<Part> allProducts, String query) {
        List<Part> newProductList = new ArrayList<>();
        for(Part part : allProducts) {
            Matcher matcher = Pattern.compile("\\b" + query + "\\b", Pattern.CASE_INSENSITIVE).matcher(part.getPartType().getType());
            while (matcher.find()) {
                newProductList.add(part);
            }
        }
        return newProductList;
    }

    private List<Part> searchByDescription (List<Part> allProducts, String query) {
        List<Part> newProductList = new ArrayList<>();
        for(Part part : allProducts) {
            Matcher matcher = Pattern.compile("\\b" + query + "\\b", Pattern.CASE_INSENSITIVE).matcher(part.getDescription());
            while (matcher.find()) {
                newProductList.add(part);
            }
        }
        return newProductList;
    }

  private List<Part> searchByCar (List<Part> allProducts, String query) {
    List<Part> newProductList = new ArrayList<>();
    for(Part part : allProducts) {
      Matcher matcher = Pattern.compile("\\b" + query + "\\b", Pattern.CASE_INSENSITIVE).matcher(part.getCar().getBrand());
      while (matcher.find()) {
        newProductList.add(part);
      }
    }
    return newProductList;
  }
}
