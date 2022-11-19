package com.home.springshope.Service;

import com.home.springshope.Dto.UserDto;
import com.home.springshope.Model.Role;
import com.home.springshope.Model.User;
import com.home.springshope.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(UserDto userDto) {
        if (!Objects.equals(userDto.getPassword(), userDto.getConfirmPassword())) {
            throw new RuntimeException("Password is wrong");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmailMail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);

        repository.save(user);


        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findFirstByName(username);

        if (user == null) {

            throw new UsernameNotFoundException("Not found user for name" + username);
        }


        List<GrantedAuthority> role = new ArrayList<>();

        role.add(new SimpleGrantedAuthority(user.getRole().name()));


        return new org.springframework.security.core.userdetails
                .User(user.getName(), user.getPassword(), role);
    }
}
