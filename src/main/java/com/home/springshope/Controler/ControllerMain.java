package com.home.springshope.Controler;

import com.home.springshope.Service.SessionClick;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class ControllerMain {

    private final SessionClick sessionClick;

    public ControllerMain(SessionClick sessionClick) {
        this.sessionClick = sessionClick;
    }

    @RequestMapping("/")
    public String index(Model model, HttpSession session) {

        model.addAttribute("amountClicks", sessionClick.getAmountClick());

        if (session.getAttribute("myID") == null) {
            String uuid = UUID.randomUUID().toString();
            session.setAttribute("myID", uuid);

            System.out.println("My uuid =" + uuid);
        }

        model.addAttribute("uuid", session.getAttribute("myID"));

        return "index";
    }

    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {

        model.addAttribute("loginError", true);

        return "login";


    }


}




