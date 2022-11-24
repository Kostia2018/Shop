package com.home.springshope.Mapper;


import com.home.springshope.Dto.ProductDto;
import com.home.springshope.Model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDto productDto);

    @InheritInverseConfiguration
    ProductDto fromProduct(Product product);


    List<Product> toProductList(List<ProductDto> productDtoList);

    List<ProductDto> fromProductList(List<Product> products);

}
