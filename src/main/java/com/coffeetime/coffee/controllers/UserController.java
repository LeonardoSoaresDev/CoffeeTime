package com.coffeetime.coffee.controllers;

import com.coffeetime.coffee.models.userModels.UserModel;
import com.coffeetime.coffee.services.userService.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public String userRegister(UserModel user){
        return userService.userRegister(user);
    }
}
