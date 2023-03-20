package com.blogger.blogapp.service;

import com.blogger.blogapp.payload.UserDto;

import java.util.List;

public interface UserService{

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Long id);
    UserDto getUserById(Long id);
    List<UserDto> getALlUsers();
    void deleteUser(Long id);
}
