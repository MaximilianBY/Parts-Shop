package by.tms.partshop.services.impl;

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
public class SearchServiceImpl {

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

    private Set<Product> searchProducts(List<Product> allProducts, String query) {
        Set<Product> products = new HashSet<>();
        products.addAll(searchByName(allProducts, query));
        products.addAll(searchByDescription(allProducts, query));
        return products;
    }

    private List<Product> searchByName (List<Product> allProducts, String query) {
        List<Product> newProductList = new ArrayList<>();
        for(Product product : allProducts) {
            Matcher matcher = Pattern.compile("\\b" + query + "\\b", Pattern.CASE_INSENSITIVE).matcher(product.getName());
            while (matcher.find()) {
                newProductList.add(product);
            }
        }
        return newProductList;
    }

    private List<Product> searchByDescription (List<Product> allProducts, String query) {
        List<Product> newProductList = new ArrayList<>();
        for(Product product : allProducts) {
            Matcher matcher = Pattern.compile("\\b" + query + "\\b", Pattern.CASE_INSENSITIVE).matcher(product.getDescription());
            while (matcher.find()) {
                newProductList.add(product);
            }
        }
        return newProductList;
    }
}
