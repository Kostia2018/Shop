package com.home.springshope.Service;

import com.home.springshope.Dto.UserDto;
import com.home.springshope.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean save(UserDto userDto);

    List<UserDto> getAll();

    void updateProfile(UserDto userDto);

    User findByName(String name);


    void save(User user);
}
