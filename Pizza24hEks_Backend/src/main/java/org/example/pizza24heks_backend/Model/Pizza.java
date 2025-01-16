package org.example.pizza24heks_backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pizza_id;

    private String title;

    private int price;


    public Long getId() {
        return pizza_id;
    }

    public Long getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(Long pizza_id) {
        this.pizza_id = pizza_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
