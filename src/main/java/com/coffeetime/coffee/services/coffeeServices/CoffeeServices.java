package com.coffeetime.coffee.services.coffeeServices;

import com.coffeetime.coffee.models.coffeeModels.CoffeeModel;
import com.coffeetime.coffee.repositorys.coffeeRepository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeServices{

    CoffeeRepository coffeeRepository;

    public CoffeeServices(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    public List<CoffeeModel> allCoffee(){
        return (List<CoffeeModel>) coffeeRepository.findAll();
    }
}
