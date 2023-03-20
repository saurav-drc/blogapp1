package com.blogger.blogapp.service.Impl;

import com.blogger.blogapp.entities.User;
import com.blogger.blogapp.exception.ResourceNotFoundException;
import com.blogger.blogapp.payload.UserDto;
import com.blogger.blogapp.repository.UserRepository;
import com.blogger.blogapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    //CREATE
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser = userRepository.save(user);
         return userToDto(savedUser);
    }
    //UPDATE
    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepository.save(user);
        return userToDto(updatedUser);
    }
    //GET BY ID
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return userToDto(user);
    }
    //GET ALL
    @Override
    public List<UserDto> getALlUsers() {
        List<User> users = userRepository.findAll();
         return users.stream().map(user -> userToDto(user)).collect(Collectors.toList());

    }
    //DELETE
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);

    }

    //Entity to Dto
    private UserDto userToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
    //Dto to Entity
    private User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);

//        User user= new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }
}
