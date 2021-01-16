package com.coffeetime.coffee.controllers;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import com.coffeetime.coffee.services.coffeeServices.CoffeeServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoffeeController {

    CoffeeServices coffeeServices;

    public CoffeeController(CoffeeServices coffeeServices){
        this.coffeeServices = coffeeServices;
    }

    @GetMapping("/coffees")
    public List<CoffeeModel> GetCoffeeList(){
        return coffeeServices.allCoffee();
    }

}
