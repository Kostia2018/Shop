package com.home.springshope.Service;

import com.home.springshope.Dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAll();

    void addToUserBucket(Long userId, String name);

    void addProduct(ProductDto dto);
}
