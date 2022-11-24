package com.home.springshope.Repository;

import com.home.springshope.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.method.P;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
