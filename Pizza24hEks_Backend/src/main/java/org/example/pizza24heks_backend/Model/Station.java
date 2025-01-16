package org.example.pizza24heks_backend.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long station_id;

    private double latitude;

    private double longitude;

    @OneToMany(mappedBy = "station")
    private List<Drone> drones;

    public Long getStation_id() {
        return station_id;
    }

    /*
    public List<Drone> getDrones() {
        return drones;
    }

    public void setStation_id(Long station_id) {
        this.station_id = station_id;
    }
    */

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public int getNumberOfDrones(){
        return drones.size();
    }
}
