package com.home.springshope.Dto;

import com.home.springshope.Model.User;

public class UserDto {

    private String username;

    private String password;

    private String email;

    private String confirmPassword;




    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }



    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }



}
