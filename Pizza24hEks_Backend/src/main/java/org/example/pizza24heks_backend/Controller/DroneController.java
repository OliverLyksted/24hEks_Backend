package org.example.pizza24heks_backend.Controller;

import org.example.pizza24heks_backend.Model.Drone;
import org.example.pizza24heks_backend.Model.OperationStatus;
import org.example.pizza24heks_backend.Model.Station;
import org.example.pizza24heks_backend.Repository.DroneRepository;
import org.example.pizza24heks_backend.Repository.StationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drones")
public class DroneController {

    private final DroneRepository droneRepository;
    private final StationRepository stationRepository;

    public DroneController(DroneRepository droneRepository, StationRepository stationRepository) {
        this.droneRepository = droneRepository;
        this.stationRepository = stationRepository;
    }

    @GetMapping()
    public List<Drone> getAllDrones() {
        List<Drone> drones = droneRepository.findAll();
        return drones;
    }

    @PostMapping("/add")
    public void addDrone() {
        List<Station> stations = stationRepository.findAll();
        if (stations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Der er ingen stationer til at tilføje dronen til.");
        }

        Station station = stations.stream().min(Comparator.comparingInt(Station::getNumberOfDrones)).orElse(null);
        if (station == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Der er ingen stationer til at tilføje dronen til.");
        } else {
            Drone drone = new Drone();
            drone.setOperationStatus(OperationStatus.I_DRIFT);
            drone.setSerial_uuid(java.util.UUID.randomUUID().toString());
            drone.setStation(station);
            droneRepository.save(drone);
        }

    }

    @PostMapping("/enable")
    public void enableDrone(@RequestParam Long drone_id) {

        Optional<Drone> optionalDrone = droneRepository.findById(drone_id);

        if (optionalDrone.isPresent()) {

            Drone drone = optionalDrone.get();
            drone.setOperationStatus(OperationStatus.I_DRIFT);
            droneRepository.save(drone);
        }
    }

    @PostMapping("/disable")
    public void disableDrone(@RequestParam Long drone_id) {

        Optional<Drone> optionalDrone = droneRepository.findById(drone_id);

        if (optionalDrone.isPresent()) {

            Drone drone = optionalDrone.get();
            drone.setOperationStatus(OperationStatus.UDE_AF_DRIFT);
            droneRepository.save(drone);
        }
    }

    @PostMapping("/retire")
    public void retireDrone(@RequestParam Long drone_id) {

        Optional<Drone> optionalDrone = droneRepository.findById(drone_id);

        if (optionalDrone.isPresent()) {

            Drone drone = optionalDrone.get();
            drone.setOperationStatus(OperationStatus.UDFASET);
            droneRepository.save(drone);
        }
    }
}
