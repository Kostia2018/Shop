package com.home.springshope.Service;

import com.home.springshope.Dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAll();

    void addToUserBucket(Long userId, String name);
}
