package com.coffeetime.coffee.repositorys.userRepository;

import com.coffeetime.coffee.models.userModels.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel,Long>{
    UserModel findByUsername (String username);
}
