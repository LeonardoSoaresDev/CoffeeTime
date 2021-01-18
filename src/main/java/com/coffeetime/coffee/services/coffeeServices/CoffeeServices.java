package com.coffeetime.coffee.services.coffeeServices;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import com.coffeetime.coffee.repositorys.coffeeRepository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeServices{

    CoffeeRepository coffeeRepository;

    //Constructor
    public CoffeeServices(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    //Getting all coffees within the database.
    public List<CoffeeModel> allCoffee(){
        return (List<CoffeeModel>) coffeeRepository.findAll();
    }

    //Getting only a single coffee, specified by the name.
    public CoffeeModel getSingleCoffee(String coffeeName){
        return coffeeRepository.findByCoffeeName(coffeeName);
    }
}
