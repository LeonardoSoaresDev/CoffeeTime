package com.coffeetime.coffee.controllers;

import com.coffeetime.coffee.models.userModels.UserModel;
import com.coffeetime.coffee.services.userService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public String userRegister(UserModel user){
        return userService.userRegister(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(UserModel userModel){
       if(userService.userLogin(userModel)){
            return new ResponseEntity<String>(String.valueOf(HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
       }
       return new ResponseEntity<String>(String.valueOf(HttpStatus.NOT_ACCEPTABLE), HttpStatus.NOT_ACCEPTABLE);
    }
}
