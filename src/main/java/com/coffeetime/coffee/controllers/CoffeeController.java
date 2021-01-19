package com.coffeetime.coffee.controllers;

import com.coffeetime.coffee.services.coffeeServices.CoffeeServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**Rest controller with HTTP method.
 * @Author: Leonardo Soares.
 * @Date:   01/19/2021.
 */

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    CoffeeServices coffeeServices;

    public CoffeeController(CoffeeServices coffeeServices){
        this.coffeeServices = coffeeServices;
    }

    /**Get method, bring a list of all coffees within the database
     *
     * @return - Return a list of coffees.
     */
    @GetMapping()
    public ResponseEntity getCoffeeList(){
        if (coffeeServices.allCoffee() == null){
            return new ResponseEntity("There's no coffees within the database", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(coffeeServices.allCoffee(),HttpStatus.OK);
    }

    //returning a coffee specified by the name.
    @GetMapping("/{coffeeName}")
    public ResponseEntity getFilteredCoffee(@PathVariable(value = "coffeeName") String coffeeName){
        if (coffeeServices.getSingleCoffee(coffeeName) == null){
            return new ResponseEntity("There's no coffees with this name!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(coffeeServices.getSingleCoffee(coffeeName),HttpStatus.OK);
    }

    //busca uma coffee pelo nome.
    @GetMapping("/filter")
    public ResponseEntity getCoffeesByParams(@RequestParam (value = "coffeeName", required = false)String coffeeName){
        return new ResponseEntity(coffeeServices.getCoffeesByParamsService(coffeeName), HttpStatus.OK);
    }
}
