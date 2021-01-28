package com.coffeetime.coffee.services.coffeeServices;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import com.coffeetime.coffee.repositorys.coffeeRepository.CoffeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**Service Class
 * @author     Leonardo Soares.
 * @since       01/19/2021
 * Attributes  coffeeRepository: CoffeeRepository instance
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

    /**Method to get only one coffee specified in the endpoint.
     *
     * There's an error if the database has more than one coffee with the same name.
     *
     * @param coffeeName    - A coffee name send by the user request.
     * @return              - Return the coffee if exist within the database.
     */
    public CoffeeModel getSingleCoffee(String coffeeName){
        if (coffeeRepository.findByCoffeeName(coffeeName) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return coffeeRepository.findByCoffeeName(coffeeName);
    }

    /**Method to filter coffees specified by the param from the requisition.
     *
     * @param coffeeName        -   A coffee name specified by the user in the request.
     * @param coffeeCountry     -   A country specified by the user in the request.
     * @return                  -   Return a list of coffee.
     */
    public List<CoffeeModel> getCoffeesByFilter(String coffeeName, String coffeeCountry){
        if (coffeeRepository.findCoffeesByParams(coffeeName, coffeeCountry).size() == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return coffeeRepository.findCoffeesByParams(coffeeName, coffeeCountry);
    }

    /**Insert new coffees to the database.
     *
     * @author Leonardo Soares.
     * @since   01/22/2021.
     *
     * @param coffee    -   CoffeeModel object from the CoffeeController.
     * @return          -   Return a String success message.
     */
    public String insertNewCoffee(CoffeeModel coffee){
        if (coffee == null || coffee.getCoffeeName().equals("") || coffee.getCountry().equals("") || coffee.getPrice() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        coffeeRepository.save(coffee);
        return "Coffee inserted with success.";
    }
}
