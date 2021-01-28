package com.coffeetime.coffee.repositorys.userRepository;

import com.coffeetime.coffee.models.userModels.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel,Long>{
    UserModel findByUsername (String username);

    /**Query made by hand.
     *
     * @param username  - Username comes from the user requisition.
     * @param password  - Password comes from the user requisition.
     * @return          - Return a user if both fields are correct.
     */
    @Query(value = "SELECT user_id, username, password FROM Users WHERE username = ?1 AND password = ?2", nativeQuery = true)
    UserModel authenticateLogin(String username, String password);
}
