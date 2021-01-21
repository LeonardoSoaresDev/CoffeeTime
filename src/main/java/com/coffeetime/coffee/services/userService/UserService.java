package com.coffeetime.coffee.services.userService;

import com.coffeetime.coffee.models.userModels.UserModel;
import com.coffeetime.coffee.repositorys.userRepository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //Change the way the password are saved later. It's just a test for now.
    public String userRegister(UserModel user){
        if (user == null || user.getUsername().equals("") || user.getPassword().equals("")){
            return "Check if all of the fields are correct!";
        }else{
            if (userRepository.findByUsername(user.getUsername()) == null){
                userRepository.save(user);
            }else{
                return "Username " + user.getUsername() +  " has already taken! Choose another one please!";
            }
        }
        return "Success";
    }

    public boolean userLogin(UserModel userModel){
        return userRepository.authenticateLogin(userModel.getUsername(), userModel.getPassword()) != null;
    }
}