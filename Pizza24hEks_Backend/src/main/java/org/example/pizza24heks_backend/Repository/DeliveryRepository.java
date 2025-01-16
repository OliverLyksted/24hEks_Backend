package org.example.pizza24heks_backend.Repository;

import org.example.pizza24heks_backend.Model.Delivery;
import org.example.pizza24heks_backend.Model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
