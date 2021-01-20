package com.coffeetime.coffee.services.coffeeServices;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import com.coffeetime.coffee.repositorys.coffeeRepository.CoffeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**Service Class
 * @Author:     Leonardo Soares.
 * @Date:       01/19/2021
 * Attributes:  coffeeRepository: CoffeeRepository instance
 */
@Service
public class CoffeeServices{

    CoffeeRepository coffeeRepository;

    /**Constructor method.
     * @param coffeeRepository Injection Dependency
     */
    public CoffeeServices(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    /**Method to get all coffees within the database
     * @return Coffee Model - Return a list of all coffees.
     */
    public List<CoffeeModel> allCoffee(){
        return (List<CoffeeModel>) coffeeRepository.findAll();
    }

    /**Method to get only a single Coffee.
     * @param coffeeName String - The name of the coffee.
     * @return Coffee Model     - A single object of coffee.
     */
    public CoffeeModel getSingleCoffee(String coffeeName){
        return coffeeRepository.findByCoffeeName(coffeeName);
    }

    /**Method to find coffees specified by the param from the requisition.
     * @param coffeeName  - Params that comes from the HTTP requisition.
     * @return            - Return a list of coffees specified by the param.
     */
    public List<CoffeeModel> getCoffeesByParamsService(String coffeeName, String coffeeCountry){

        if (coffeeRepository.findCoffeesByParams(coffeeName, coffeeCountry).size() == 0){
            System.out.println("Não encontrou nada no database!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return coffeeRepository.findCoffeesByParams(coffeeName, coffeeCountry);
    }
}
