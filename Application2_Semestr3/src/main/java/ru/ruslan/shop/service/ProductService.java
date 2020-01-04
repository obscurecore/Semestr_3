package ru.ruslan.shop.service;



import ru.ruslan.shop.entity.*;

import java.util.List;

/**
 * 
 * @author Potapov Ruslan
 */
public interface ProductService {

    List<Product> listAllProducts(int page, int limit);

    int countAllProducts();

    List<Product> listProductsByCategory(String categoryUrl, int page, int limit);

    int countProductsByCategory(String categoryUrl);

    List<Category> listAllCategories();

    List<Producer> listAllProducers();

   // List<Product> listProductsBySearchForm(SearchForm searchForm, int page, int limit);

    //int countProductsBySearchForm(SearchForm searchForm);
}

