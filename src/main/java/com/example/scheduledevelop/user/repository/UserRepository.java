package com.example.scheduledevelop.user.repository;


import com.example.scheduledevelop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> id(Long id);
}
