package com.coffeetime.coffee.services.userService;

import com.coffeetime.coffee.models.userModels.UserModel;
import com.coffeetime.coffee.repositories.userRepository.UserRepository;
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
        var userValidation = isValid(user);
        var userDB = userRepository;

        if (!userValidation){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }

        if (userDB.findByUsername(user.getUsername()) != null) {
            return "Username " + user.getUsername() + " has already taken! Choose another one please!";
        }
        userDB.save(user);
        return "Success";
    }

    /**User Login method.
     *
     * @param user      -   User object passed by the client in a form.
     * @return          -   Return true if the user has the right credentials or a status code error.
     */
    public boolean userLogin(UserModel user){
        var userValidation = isValid(user);
        if (!userValidation){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
        var userLoginRepository = userRepository.authenticateLogin(user.getUsername(),user.getPassword());
        return userLoginRepository != null;
    }

    /**Validation method.
     * @author Leonardo Soares
     * @since  01/29/2021
     * @param user  - User object passed by the methods that implements this method.
     * @return      - Returns true the fields is not empty or false if its empty.
     */
    private boolean isValid(UserModel user){
        return user != null &&
                user.getUsername() != null &&
                !user.getUsername().equals("") &&
                user.getPassword() != null &&
                !user.getPassword().equals("");

    }
}