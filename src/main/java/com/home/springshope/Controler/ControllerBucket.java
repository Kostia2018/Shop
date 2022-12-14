package com.home.springshope.Controler;

import com.home.springshope.Dto.BucketDto;
import com.home.springshope.Service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class ControllerBucket {


    private final BucketService bucketService;

    @Autowired
    public ControllerBucket(BucketService bucketService) {
        this.bucketService = bucketService;
    }


    @GetMapping("/bucket")
    public String aboutBucket(Model model, Principal principal) {


        if (principal == null) {

            model.addAttribute("bucket", new BucketDto());
        } else {

            BucketDto bucketDto = bucketService.getBucketByUser(principal.getName());

            model.addAttribute("bucket", bucketDto);

        }


        return "bucket";

    }

    @PostMapping("/bucket")
    public String commitBucket(Principal principal) {

        if (principal != null) {
            bucketService.commitBucketToOrder(principal.getName());
        }

        return "redirect:/bucket";

    }


}
