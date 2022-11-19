package com.home.springshope.Controler;


import com.home.springshope.Dto.UserDto;
import com.home.springshope.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class ControllerUser {

    private final UserService service;

    @Autowired
    public ControllerUser(UserService service) {
        this.service = service;
    }

    @GetMapping("/new")
    public String newUser(Model model) {

        model.addAttribute("user", new UserDto());

        return "user";
    }

    @PostMapping("/new")
    public String saveUser(Model model, UserDto userDto) {

        if (service.save(userDto)) {

            return "redirect:/";
        } else {
            model.addAttribute("user", userDto);
            return "user";

        }


    }


}
