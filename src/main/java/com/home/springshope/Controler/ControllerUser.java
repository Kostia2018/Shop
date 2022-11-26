package com.home.springshope.Controler;


import com.home.springshope.Dto.UserDto;
import com.home.springshope.Model.User;
import com.home.springshope.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class ControllerUser {

    private final UserService service;

    @Autowired
    public ControllerUser(UserService service) {
        this.service = service;
    }


    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", service.getAll());

        return "userlist";

    }


    @GetMapping("/new")
    public String newUser(Model model) {

        model.addAttribute("user", new UserDto());

        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDto userDto, Model model) {

        if (service.save(userDto)) {

            return "redirect:/users";
        } else {
            model.addAttribute("user", userDto);
            return "user";

        }


    }


    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal) {

        if (principal == null) {
            throw new RuntimeException("User is not authorized");
        }


        User user = service.findByName(principal.getName());

        UserDto userDto = new UserDto();

        userDto.setUsername(user.getName());
        userDto.setEmail(user.getEmailMail());


        model.addAttribute("user", userDto);

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(Model model, Principal principal, UserDto userDto) {

        if (principal == null || !Objects.equals(userDto.getUsername(), principal.getName())) {
            throw new RuntimeException("User is not authorized");
        }

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()
                && Objects.equals(userDto.getPassword(), userDto.getConfirmPassword())) {

            model.addAttribute("user", userDto);

            return "profile";

        }

        service.updateProfile(userDto);

        return "redirect:/users/profile";

    }

}
