package com.home.springshope.Service;

import com.home.springshope.Dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean save(UserDto userDto);

    List<UserDto> getAll();

}
