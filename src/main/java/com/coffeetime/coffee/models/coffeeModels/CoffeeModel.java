package com.coffeetime.coffee.models.coffeeModels;

import javax.persistence.*;

/**Entity class for database integration
 * @author Leonardo Soares.
 * @since  01/19/2021.
 */
@Entity
@Table(name = "Coffees")
public class CoffeeModel {

    /**
     * Attributes:
     *              coffeeId:       Long
     *              coffeeName:     String
     *              country:        String
     *              price:          double
     *              isCold:         boolean
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coffeeId;
    private String coffeeName;
    private String country;
    private double price;
    private boolean isCold;

    public CoffeeModel(Long coffeeId, String coffeeName, String country, double price) {
        this.coffeeId = coffeeId;
        this.coffeeName = coffeeName;
        this.country = country;
        this.price = price;
    }

    @SuppressWarnings("unsed")
    public CoffeeModel(){

    }

    public Long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(Long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isCold() {
        return isCold;
    }

    public void setCold(boolean cold) {
        isCold = cold;
    }
}
