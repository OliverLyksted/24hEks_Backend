package org.example.pizza24heks_backend.Controller;

import org.example.pizza24heks_backend.Model.Delivery;
import org.example.pizza24heks_backend.Model.Drone;
import org.example.pizza24heks_backend.Model.Pizza;
import org.example.pizza24heks_backend.Repository.DeliveryRepository;
import org.example.pizza24heks_backend.Repository.DroneRepository;
import org.example.pizza24heks_backend.Repository.PizzaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private DeliveryRepository deliveryRepository;
    private DroneRepository droneRepository;
    private final PizzaRepository pizzaRepository;


    public DeliveryController(DeliveryRepository deliveryRepository, DroneRepository droneRepository, PizzaRepository pizzaRepository) {
        this.deliveryRepository = deliveryRepository;
        this.droneRepository = droneRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping()
    public List<Delivery> getAllNotFinishDeliveries() {

        List<Delivery> deliveriesFromDatabase = deliveryRepository.findAll();
        List<Delivery> deliveries = new ArrayList<>();

        for (Delivery delivery : deliveriesFromDatabase) {

            if (delivery.getActual_delivery_date() == null) {
                deliveries.add(delivery);
            }
        }

        return deliveries;
    }

    @PostMapping("/add")
    public void addDelivery(Long pizza_id) {

        Optional<Pizza> optionalPizza = pizzaRepository.findById(pizza_id);
        if (optionalPizza.isPresent()) {
            Pizza pizza = optionalPizza.get();
            Delivery delivery = new Delivery();
            delivery.setPizza(pizza);
            delivery.setExpected_delivery_date(LocalDateTime.now().plusMinutes(30));
            deliveryRepository.save(delivery);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pizza_id findes ikke.");
        }
    }


    @GetMapping("/queue")
    public List<Delivery> getQueue() {

        List<Delivery> deliveriesFromDatabase = deliveryRepository.findAll();
        List<Delivery> deliveries = new ArrayList<>();

        for (Delivery delivery : deliveriesFromDatabase) {

            if (delivery.getDrone() == null) {
                deliveries.add(delivery);
            }
        }

        return deliveries;
    }

    @PostMapping("/schedule")
    public void scheduleDelivery(Long delivery_id) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(delivery_id);
        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();

            if (delivery.getDrone() == null)
            {
                List<Drone> drones = droneRepository.findAll();

                for (Drone drone : drones) {
                    if (drone.hasNoDeliveries()) {
                        delivery.setDrone(drone);
                        deliveryRepository.save(delivery);
                        break;
                    }
                }
            }
        }
    }


    @PostMapping("/finish")
    public void finishDelivery(@RequestParam Long delivery_id) throws Exception {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(delivery_id);

        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();

            if (delivery.getDrone() == null) {
                throw new Exception("Leveringen har ikke nogen drone tilknyttet");
                // TODO: Her bør man return f.eks. fejl 400 for at indikere at klienten prøver at afsluttet en levering uden drone og det kan man ikke.
            }

            delivery.setActual_delivery_date(LocalDateTime.now());
            deliveryRepository.save(delivery);
        }
    }

}
