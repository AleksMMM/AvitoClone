package com.avito.service;

import com.avito.models.Product;
import com.avito.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private List<Product> products = new ArrayList<>();
    private long ID = 0;

    {
        products.add(new Product(++ID, "PlayStation 5", "Simple description", 67000, "Krasnoyarsk", "tomas"));
        products.add(new Product(++ID, "Iphone 8", "Simple description", 24000, "Moscow", "artmcoder"));
    }

    public List<Product> listTitle(String title) {
        List<Product> all = productRepository.findAll();
        if (title != null) {

          return all.stream()
                  .filter(p -> p.getTitle()
                  .equals(title))
                  .collect(Collectors.toList());
        }
        return all;
    }

    public void saveProduct(Product product) {
        log.info("saving product {}", product);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id).get();

    }
}