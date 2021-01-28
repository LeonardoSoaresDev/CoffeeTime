package com.coffeetime.coffee.api;

import com.coffeetime.coffee.models.userModels.UserModel;
import com.coffeetime.coffee.services.userService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**User Controller
 * @author Leonardo Soares
 */

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    /**Register route
     *
     * @param user  -   User object, should have username and password passed by a form.
     * @return      -   Success message if the user complete the register or an error if the user didn't pass the field correctly.
     */
    @PostMapping(value = "/register")
    public String userRegister(UserModel user){
        return userService.userRegister(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserModel userModel){
       if(userService.userLogin(userModel)){
            return new ResponseEntity<>(String.valueOf(HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
       }
       return new ResponseEntity<>(String.valueOf(HttpStatus.NOT_ACCEPTABLE), HttpStatus.NOT_ACCEPTABLE);
    }
}
