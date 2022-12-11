package com.home.springshope.Service;

import com.home.springshope.Dto.ProductDto;
import com.home.springshope.Mapper.ProductMapper;
import com.home.springshope.Model.Bucket;
import com.home.springshope.Model.Product;
import com.home.springshope.Model.User;
import com.home.springshope.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;

    private final UserService userService;

    private final BucketService bucketService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              UserService userService, BucketService bucketService,
                              SimpMessagingTemplate simpMessagingTemplate) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public List<ProductDto> getAll() {


        List<Product> productList = productRepository.findAll();


        return mapper.fromProductList(productList);
    }


    @Override
    public void addToUserBucket(Long productId, String name) {

        User user = userService.findByName(name);

        if (user == null) {
            throw new RuntimeException("User not faund " + name);
        }


        Bucket bucket = user.getBucket();

        if (bucket == null) {

            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));

            user.setBucket(newBucket);

            userService.save(user);
        } else {

            bucketService.addProducts(bucket, Collections.singletonList(productId));

        }


    }

    @Override
    @Transactional
    public void addProduct(ProductDto dto) {

        Product product = mapper.toProduct(dto);

        Product productSave = productRepository.save(product);

        simpMessagingTemplate.convertAndSend("/topic/products", ProductMapper.MAPPER.fromProduct(productSave));

    }


}
