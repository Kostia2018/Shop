package com.home.springshope.Service;

import com.home.springshope.Dto.BucketDetailDto;
import com.home.springshope.Dto.BucketDto;
import com.home.springshope.Model.*;
import com.home.springshope.Repository.BucketRepository;
import com.home.springshope.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class BucketServiceImpl implements BucketService {

    private final ProductRepository productRepository;

    private final BucketRepository bucketRepository;

    private final UserService userService;

    private final OrderService orderService;


    @Autowired
    public BucketServiceImpl(ProductRepository productRepository, BucketRepository bucketRepository,
                             UserService userService, OrderService orderService) {
        this.productRepository = productRepository;
        this.bucketRepository = bucketRepository;
        this.userService = userService;
        this.orderService = orderService;
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

        return productId.stream().map(p -> productRepository.getOne(p))
                .collect(Collectors.toList());


    }


    @Override
    public void addProducts(Bucket bucket, List<Long> productId) {
        List<Product> listProduct = bucket.getProducts();

        List<Product> newListProduct = (listProduct == null ? new ArrayList<>() : new ArrayList<>(listProduct));

        newListProduct.addAll(getRefProductsFromId(productId));

        bucket.setProducts(newListProduct);

        bucketRepository.save(bucket);


    }

    @Override
    public BucketDto getBucketByUser(String name) {

        User user = userService.findByName(name);

        if (user == null || user.getBucket() == null) {
            return new BucketDto();
        }


        BucketDto newBucketDto = new BucketDto();

        Map<Long, BucketDetailDto> mapByProductIs = new HashMap<>();

        List<Product> products = user.getBucket().getProducts();

        for (Product p : products) {

            BucketDetailDto detail = mapByProductIs.get(p.getId());

            if (detail == null) {

                mapByProductIs.put(p.getId(), new BucketDetailDto(p));
            } else {

                detail.setAmount(detail.getAmount().add(BigDecimal.valueOf(1.0)));

                detail.setSum(detail.getSum().add(p.getPrice()));
            }
        }

        newBucketDto.setDetailsBucket(new ArrayList<>(mapByProductIs.values()));

        newBucketDto.aggregate();


        return newBucketDto;
    }


    @Override
    @Transactional
    public void commitBucketToOrder(String name) {

        User user = userService.findByName(name);

        if (user == null) {

            throw new RuntimeException("User is not found");

        }


        Bucket bucket = user.getBucket();

        if (bucket == null || bucket.getProducts().isEmpty()) {

            return;

        }


        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.NEW);


        Map<Product, Long> productAmount = bucket.getProducts().stream()
                .collect(Collectors.groupingBy(prod -> prod, Collectors.counting()));


        List<OrderDetails> orderDetails = productAmount.entrySet().stream()
                .map(p -> new OrderDetails(order, p.getKey(), p.getValue()))
                .collect(Collectors.toList());


        BigDecimal sum = BigDecimal.valueOf(orderDetails.stream()
                .map(det -> det.getPrice().multiply(det.getAmount()))
                .mapToDouble(BigDecimal::doubleValue).sum());


        order.setSum(sum);
        order.setDetails(orderDetails);
        order.setAddress("no address");


        orderService.saveOrder(order);
        bucket.getProducts().clear();
        bucketRepository.save(bucket);


    }


}
