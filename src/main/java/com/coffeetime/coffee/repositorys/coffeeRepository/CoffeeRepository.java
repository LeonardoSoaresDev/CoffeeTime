package com.coffeetime.coffee.repositorys.coffeeRepository;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<CoffeeModel,Long> {
    CoffeeModel findByCoffeeName(String coffeeName);
}
