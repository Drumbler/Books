package com.example.books.service;

import com.example.books.dto.UserDto;
import com.example.books.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
