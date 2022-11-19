package com.home.springshope.Service;

import com.home.springshope.Dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean save(UserDto userDto);

}
