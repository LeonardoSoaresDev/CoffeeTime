package com.coffeetime.coffee.services.coffeeServices;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import com.coffeetime.coffee.repositories.coffeeRepository.CoffeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        var coffee = coffeeRepository.findByCoffeeName(coffeeName);
        if (coffee == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No coffee was found");
        }
        return coffee;
    }

    /**Method to filter coffees specified by the param from the requisition.
     *
     * @param coffeeName        -   A coffee name specified by the user in the request.
     * @param coffeeCountry     -   A country specified by the user in the request.
     * @return                  -   Return a list of coffee.
     */
    public List<CoffeeModel> getCoffeesByFilter(String coffeeName, String coffeeCountry){
        var coffeeList = coffeeRepository.findCoffeesByParams(coffeeName,coffeeCountry);
        if (coffeeList.size() == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists coffees with this params");
        }
        return coffeeList;
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
        var coffeeValidation = isValid(coffee);

        if (!coffeeValidation){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "You need to fill all fields");
        }

        var coffeeDB = coffeeRepository.findByCoffeeName(coffee.getCoffeeName());

        if (coffeeDB != null && !coffeeDB.getCoffeeName().equals("")){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "The coffee name already exists in the database");
        }

        coffeeRepository.save(coffee);
        return "Coffee inserted with success.";
    }

    public String deleteCoffeeService(String coffeeName){
        var coffeeDB = coffeeRepository.findByCoffeeName(coffeeName);

        if (coffeeDB == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There's no coffee with this name!");
        }
        coffeeRepository.delete(coffeeDB);
        return "Deleted!";
    }

    public String updateCoffeeService(String coffeeName, double price){

        if (coffeeName == null || coffeeName.equals("") || price == 0 || price < 0 || coffeeRepository.updateCoffeeByName(coffeeName,price) < 1){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Verify if all fields are correct");
        }
        return "Update success!";
    }

    /**Validation field method.
     *
     * @author Leonardo Soares
     * @since  01/29/2021
     * @param coffee    - Coffee object from the methods that uses this method.
     * @return          - Return true if non of the field are empty.
     */
    private boolean isValid(CoffeeModel coffee){
        return coffee != null &&
                coffee.getCoffeeName() != null &&
                !coffee.getCoffeeName().equals("") &&
                coffee.getCountry() != null &&
                !coffee.getCountry().equals("") &&
                coffee.getPrice() != 0;
    }
}
