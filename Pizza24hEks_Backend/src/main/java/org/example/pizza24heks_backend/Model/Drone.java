package org.example.pizza24heks_backend.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Drone {

    public Drone() {

    }

    public Drone(String serial_uuid, OperationStatus operationStatus) {
        this.serial_uuid = serial_uuid;
        this.operationStatus = operationStatus;
        this.deliveries = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drone_id;

    private String serial_uuid;

    private OperationStatus operationStatus;

    @ManyToOne
     private Station station;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)  // TODO: undersøg mappedby
    private List<Delivery> deliveries;

    public Long getDrone_id() {
        return drone_id;
    }

    public void setDrone_id(Long drone_id) {
        this.drone_id = drone_id;
    }

    public String getSerial_uuid() {
        return serial_uuid;
    }

    public void setSerial_uuid(String serial_uuid) {
        this.serial_uuid = serial_uuid;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public boolean hasNoDeliveries() {
        return deliveries.isEmpty();
    }
}
