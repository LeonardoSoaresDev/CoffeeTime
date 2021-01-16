package com.coffeetime.coffee.repositorys.userRepository;

import com.coffeetime.coffee.models.userModels.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel,Long> {
}
