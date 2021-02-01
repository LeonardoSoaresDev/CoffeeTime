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
        return new ResponseEntity<>(coffeeServices.allCoffee(), HttpStatus.OK);
    }

    /**Get method to bring only one coffee specified by the name.
     *
     * @param coffeeName    -   A coffee name
     * @return              -   Return a coffee.
     */
    @GetMapping("/{coffeeName}")
    public ResponseEntity<?> getCoffee(@PathVariable(value = "coffeeName") String coffeeName){
        return new ResponseEntity<>(coffeeServices.getSingleCoffee(coffeeName),HttpStatus.OK);
    }

    /**Method to get a coffee by the name or country
     *
     * @param coffeeName        -   A coffee name that comes from the requisition.
     * @param coffeeCountry     -   A coffee country that comes from the requisition.
     * @return                  -   Return coffees that has the name or the country specified.
     */
    @GetMapping("/filter")
    public ResponseEntity<?> getCoffeesByParams(
            @RequestParam (value = "coffeeName", required = false, defaultValue = "")String coffeeName,
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
        return new ResponseEntity<>(coffeeServices.insertNewCoffee(coffee), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCoffee(@RequestParam (name = "coffeeName") String coffeeName){
        return new ResponseEntity<>(coffeeServices.deleteCoffeeService(coffeeName), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateCoffee(@RequestParam(name = "coffeeName") String coffeeName, double price){
        return new ResponseEntity<>(coffeeServices.updateCoffeeService(coffeeName, price), HttpStatus.OK);
    }
}
