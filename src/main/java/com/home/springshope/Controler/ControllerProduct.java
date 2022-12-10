package com.home.springshope.Controler;


import com.home.springshope.Dto.ProductDto;
import com.home.springshope.Repository.ProductRepository;
import com.home.springshope.Service.ProductService;
import com.home.springshope.Service.SessionClick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ControllerProduct {

    private final ProductService productService;
    private final SessionClick sessionClick;
    private final ProductRepository productRepository;

    @Autowired
    public ControllerProduct(ProductService productService, SessionClick sessionClick,
                             ProductRepository productRepository) {
        this.productService = productService;
        this.sessionClick = sessionClick;
        this.productRepository = productRepository;
    }


    @RequestMapping
    public String getAllProducts(Model model) {

        sessionClick.addClick();

        model.addAttribute("products", productService.getAll());


        return "products";

    }

    @GetMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal) {

        sessionClick.addClick();

        if (principal == null) {

            return "redirect:/products";
        }


        productService.addToUserBucket(id, principal.getName());

        return "redirect:/products";


    }

    @PostMapping
    public ResponseEntity<Void> addProduct(ProductDto dto) {

        productService.addProduct(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();


    }


}
