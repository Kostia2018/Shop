package com.home.springshope.EndPoint;

import com.home.springshope.Dto.ProductDto;
import com.home.springshope.Service.ProductService;
import com.home.springshope.ws.greeting.GetGreetingResponse;
import com.home.springshope.ws.products.GetProductsRequest;
import com.home.springshope.ws.products.GetProductsResponse;
import com.home.springshope.ws.products.ProductsWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductsEndpoint {

    private static final String NAME_SPASE_PROD = "http://home.com/springshope/ws/products";


    private final ProductService productService;

    @Autowired
    public ProductsEndpoint(ProductService productService) {
        this.productService = productService;
    }


    @PayloadRoot(namespace = NAME_SPASE_PROD, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProductWS(@RequestPayload GetProductsRequest getProductsRequest) {

        GetProductsResponse response = new GetProductsResponse();

        productService.getAll().forEach(dt -> response.getProducts().add(createProductWs(dt)));

        return response;

    }


    private ProductsWS createProductWs(ProductDto productDto) {

        ProductsWS productsWS = new ProductsWS();

        productsWS.setId(productDto.getId());

        productsWS.setPrice(Double.parseDouble(productDto.getPrice().toString()));

        productsWS.setTitle(productDto.getTitle());

        return productsWS;


    }


}
