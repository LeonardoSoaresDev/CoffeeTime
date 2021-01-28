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

    //This part of the project should be used with authentication token and the right ways to do authentications.
    public String userRegister(UserModel user){
        if (user == null || user.getUsername() == null || user.getPassword()== null || user.getUsername().equals("") || user.getPassword().equals("")){
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

    /**User Login method.
     *
     * @param userModel -   User object passed by the client in a form.
     * @return          -   Return true if the user has the right credentials or a status code error if
     */
    public boolean userLogin(UserModel userModel){
        if (userModel == null || userModel.getUsername() == null || userModel.getPassword() == null || userModel.getUsername().equals("") || userModel.getPassword().equals("")){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
        return userRepository.authenticateLogin(userModel.getUsername(), userModel.getPassword()) != null;
    }
}