package com.coffeetime.coffee.repositories.coffeeRepository;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**Repository interface.
 *
 * @author   Leonardo Soares.
 * @since     01/19/2021.
 * This interface extends another interface which has crud methods ready to be used.
 */
public interface CoffeeRepository extends CrudRepository<CoffeeModel,Long> {
    /**Custom query method.
     *
     * @param coffeeName - String - Name of a coffee.
     * @return           - Return a coffee object with all information about it. (Id, Name, Country, Price).
     */
    List<CoffeeModel> findByCoffeeName(String coffeeName);

    /**Another Custom query write by hand finding the coffee by the name or by the country.
     *
     * @param coffeeName    -   String - coffee name from request
     * @param coffeeCountry -   String - coffee country from request
     * @return              -   return a list of coffee which match with the query.
     */
    @Query(value = "SELECT coffee_id, coffee_name, country, is_cold, price FROM Coffees WHERE coffee_name = ?1 OR country = ?2",
            nativeQuery = true)
    List<CoffeeModel> findCoffeesByParams(String coffeeName, String coffeeCountry);
}
