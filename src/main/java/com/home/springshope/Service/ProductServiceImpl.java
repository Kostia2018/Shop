package com.home.springshope.Service;

import com.home.springshope.Dto.ProductDto;
import com.home.springshope.Mapper.ProductMapper;
import com.home.springshope.Model.Product;
import com.home.springshope.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAll() {


        List<Product> productList = productRepository.findAll();


        return mapper.fromProductList(productList);
    }
}
