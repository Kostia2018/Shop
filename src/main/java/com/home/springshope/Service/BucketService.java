package com.home.springshope.Service;

import com.home.springshope.Dto.BucketDto;
import com.home.springshope.Model.Bucket;
import com.home.springshope.Model.Product;
import com.home.springshope.Model.User;

import java.util.List;

public interface BucketService {

    Bucket createBucket(User user, List<Long> productId);

    void addProducts(Bucket bucket, List<Long> productId);

    BucketDto getBucketByUser(String name);


    public void commitBucketToOrder(String name);

}
