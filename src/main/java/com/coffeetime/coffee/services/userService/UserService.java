package com.coffeetime.coffee.services.userService;

import com.coffeetime.coffee.models.userModels.UserModel;
import com.coffeetime.coffee.repositorys.userRepository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**NOTE:
 * This part of the project should be used with authentication token and the right ways to do authentications.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**Constructor method.
     *
     * @param userRepository    - Injection Dependency.
     */
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**Register method
     *
     * @param user  - UserModel object passed by the user requisition.
     * @return      - Return a message if the user registration is successful or an error.
     */
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
     * @return          -   Return true if the user has the right credentials or a status code error.
     */
    public boolean userLogin(UserModel userModel){
        if (userModel == null || userModel.getUsername() == null || userModel.getPassword() == null || userModel.getUsername().equals("") || userModel.getPassword().equals("")){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
        return userRepository.authenticateLogin(userModel.getUsername(), userModel.getPassword()) != null;
    }
}