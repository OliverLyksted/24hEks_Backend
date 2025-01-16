package org.example.pizza24heks_backend.Repository;

import org.example.pizza24heks_backend.Model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
