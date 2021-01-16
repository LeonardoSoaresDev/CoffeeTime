package com.coffeetime.coffee.services.userService;

import com.coffeetime.coffee.models.userModels.UserModel;
import com.coffeetime.coffee.repositorys.userRepository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String userRegister(UserModel user){
        if (user == null || user.getUsername().equals("") || user.getPassword().equals("")){
            return "Confira se todos os campos estão preenchidos!";
        }else{
            userRepository.save(user);
        }
        return "Sucesso";
    }
}
//MUDAR COMITT, TIRAR SENHAS DO DATABASE FILE