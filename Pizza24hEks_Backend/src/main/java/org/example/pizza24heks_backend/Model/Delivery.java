package org.example.pizza24heks_backend.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long delivery_id;

    private String adresse;

    private LocalDateTime expected_delivery_date;

    private LocalDateTime actual_delivery_date; // TODO: Skal denne være nullable?

    @OneToOne(cascade = CascadeType.ALL)  // TODO: undersøg cascade
    private Pizza pizza;

    @ManyToOne(cascade = CascadeType.ALL)  // TODO: undersøg cascade
    private Drone drone; // TODO: undersøg mannytoOne

    public Long getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(Long levering_id) {
        this.delivery_id = levering_id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDateTime getExpected_delivery_date() {
        return expected_delivery_date;
    }


    public void setExpected_delivery_date(LocalDateTime expected_delivery) {
        this.expected_delivery_date = expected_delivery;
    }

    public LocalDateTime getActual_delivery_date() {
        return actual_delivery_date;
    }

    public void setActual_delivery_date(LocalDateTime actual_delivery) {
        this.actual_delivery_date = actual_delivery;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
