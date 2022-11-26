package com.home.springshope.Service;

import com.home.springshope.Model.Bucket;
import com.home.springshope.Model.Product;
import com.home.springshope.Model.User;
import com.home.springshope.Repository.BucketRepository;
import com.home.springshope.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BucketServiceImpl implements BucketService {

    private final ProductRepository productRepository;

    private final BucketRepository bucketRepository;


    @Autowired
    public BucketServiceImpl(ProductRepository productRepository, BucketRepository bucketRepository) {
        this.productRepository = productRepository;
        this.bucketRepository = bucketRepository;
    }


    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productId) {

        Bucket newBucket = new Bucket();

        newBucket.setUser(user);

        newBucket.setProducts(getRefProductsFromId(productId));

        return bucketRepository.save(newBucket);
    }


    private List<Product> getRefProductsFromId(List<Long> productId) {

        return productId.stream().map(p -> productRepository.getById(p))
                .collect(Collectors.toList());


    }


    @Override
    public void addProducts(Bucket bucket, List<Long> productId) {
        List<Product> listProduct = bucket.getProducts();

        List<Product> newListProduct = (listProduct == null ? new ArrayList<>() : new ArrayList<>(listProduct));

        newListProduct.addAll(getRefProductsFromId(productId));

        bucketRepository.save(bucket);


    }
}
