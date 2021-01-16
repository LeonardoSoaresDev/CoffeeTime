package com.coffeetime.coffee.repositorys.coffeeRepository;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends CrudRepository<CoffeeModel,Long> {
}
