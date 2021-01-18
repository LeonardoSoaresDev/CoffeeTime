package com.coffeetime.coffee.controllers;

import com.coffeetime.coffee.services.coffeeServices.CoffeeServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    CoffeeServices coffeeServices;

    public CoffeeController(CoffeeServices coffeeServices){
        this.coffeeServices = coffeeServices;
    }

    //returning all of the coffees within the database.
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
}
