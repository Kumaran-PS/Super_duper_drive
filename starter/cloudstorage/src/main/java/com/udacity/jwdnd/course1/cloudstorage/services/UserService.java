package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;


// progrma flows from Controller to the service
// responsible for provides services like adding , updading , deleting the user details

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public int createUser(User user) {
//        System.out.println(user);
        String encodedSalt = Salt();
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insertUserToCloud(new User(null,
                user.getUserName(),
                encodedSalt,
                hashedPassword,
                user.getFirstName(),
                user.getLastName()));

    }

    public User getUser(String username) {
//        System.out.println(username);
        User users = userMapper.getUserByName(username);
        return users;
    }

    public boolean isUsernameAvailable(String username) {
        Boolean bool = userMapper.getUserByName(username) == null;
        return bool;
    }

    private String Salt() {
        SecureRandom rand = new SecureRandom();
        byte[] salt = new byte[16];
        rand.nextBytes(salt);
        String Keys = Base64.getEncoder().encodeToString(salt);
        return Keys;
    }



}