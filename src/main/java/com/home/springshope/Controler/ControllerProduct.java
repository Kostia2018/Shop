package com.home.springshope.Controler;


import com.home.springshope.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("products")
public class ControllerProduct {

    private final ProductService productService;

    @Autowired
    public ControllerProduct(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping
    public String getAllProducts(Model model) {


        model.addAttribute("products", productService.getAll());


        return "products";

    }

    @GetMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal) {

        if (principal == null) {

            return "redirect:/products";
        }


        productService.addToUserBucket(id, principal.getName());

        return "redirect:/products";


    }


}
