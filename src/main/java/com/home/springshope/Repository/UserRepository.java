package com.home.springshope.Repository;

import com.home.springshope.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findFirstByName(String name);


}
