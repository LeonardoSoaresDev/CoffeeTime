package com.coffeetime.coffee.repositorys.coffeeRepository;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**Repository interface.
 * @Author: Leonardo Soares.
 * @Date:   01/19/2021.
 * This interface extends another interface which has crud methods ready to be used.
 */
public interface CoffeeRepository extends CrudRepository<CoffeeModel,Long> {
    /**Custom query method.
     *
     * @param coffeeName - Name of a coffee.
     * @return - Return a coffee object with all information about it. (Id, Name, Country, Price).
     */
    CoffeeModel findByCoffeeName(String coffeeName);

    /**Another Custom query write by hand.
     *
     * @param coffeeName
     * @return
     */
    //buscando uma lista de cafés pelo nome
    @Query(value = "SELECT coffee_id, coffee_name, country, is_cold, price FROM Coffees WHERE coffee_name = ?1 ",
            nativeQuery = true)
    List<CoffeeModel> findCoffeesByParams(String coffeeName);
}
