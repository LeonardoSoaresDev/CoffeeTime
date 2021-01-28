package com.coffeetime.coffee.api;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import com.coffeetime.coffee.services.coffeeServices.CoffeeServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**Rest controller with HTTP method.
 * @author  Leonardo Soares.
 * @since   01/19/2021.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class CoffeeController {

    //Injection Dependency
    CoffeeServices coffeeServices;

    /**Constructor method.
     *
     * @param coffeeServices    - Injection Dependency.
     */
    public CoffeeController(CoffeeServices coffeeServices){
        this.coffeeServices = coffeeServices;
    }

    /**Get method, bring a list of all coffees within the database
     *
     * @return - Return a list of coffees.
     */
    @GetMapping()
    public ResponseEntity<?> getCoffeeList(){
        if (coffeeServices.allCoffee() == null){
            return new ResponseEntity<String>("There's no coffees within the database", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<CoffeeModel>>(coffeeServices.allCoffee(), HttpStatus.OK);
    }

    //returning a coffee specified by the name.
    @GetMapping("/{coffeeName}")
    public ResponseEntity<CoffeeModel> getFilteredCoffee(@PathVariable(value = "coffeeName") String coffeeName){
        if (coffeeServices.getSingleCoffee(coffeeName) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CoffeeModel>(coffeeServices.getSingleCoffee(coffeeName),HttpStatus.OK);
    }

    /**Method to get a coffee by the name or country
     *
     * @param coffeeName        -   A coffee name that comes from the requisition.
     * @param coffeeCountry     -   A coffee country that comes from the requisition.
     * @return                  -   Return coffees that has the name or the country specified.
     */
    @GetMapping("/filter")
    public ResponseEntity<?> getCoffeesByParams(@RequestParam (value = "coffeeName", required = false, defaultValue = "")String coffeeName,
                                             @RequestParam (value = "coffeeCountry", required = false, defaultValue = "") String coffeeCountry){
        return new ResponseEntity<>(coffeeServices.getCoffeesByFilter(coffeeName, coffeeCountry), HttpStatus.OK);
    }

    /**Post method, insert a new coffee to the database.
     *
     * @author              Leonardo Soares.
     * @since                01/22/2021.
     *
     * @param coffee    -   Object of CoffeeModel class.
     * @return          -   Return a String message and HTTP status code.
     */
    @PostMapping()
    public ResponseEntity<?> postNewCoffee(CoffeeModel coffee){
        return new ResponseEntity<String>(coffeeServices.insertNewCoffee(coffee), HttpStatus.OK);
    }
}
