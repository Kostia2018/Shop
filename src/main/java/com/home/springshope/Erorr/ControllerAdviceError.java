package com.home.springshope.Erorr;


import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdviceError {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(Exception ex, Model model) {

        String messagErrore = (ex == null ? "Unknown exception" : ex.getMessage());

        model.addAttribute("ErrorMessage", messagErrore);

        return "error";

    }
}
