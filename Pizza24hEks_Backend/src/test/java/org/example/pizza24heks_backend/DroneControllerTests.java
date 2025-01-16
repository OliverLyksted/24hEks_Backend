package org.example.pizza24heks_backend;

import org.example.pizza24heks_backend.Controller.DroneController;
import org.example.pizza24heks_backend.Model.Drone;
import org.example.pizza24heks_backend.Model.OperationStatus;
import org.example.pizza24heks_backend.Repository.DroneRepository;
import org.example.pizza24heks_backend.Repository.StationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class DroneControllerTests {

    @Test
    void enableDrone_OperationStatusIsUdfaset_OperationStatusShouldChangeToIDrift() {

        // Arrange
        Long drone_id = 1L;
        DroneRepository mockDroneRepository = Mockito.mock(DroneRepository.class);
        StationRepository mockStationRepository = Mockito.mock(StationRepository.class);
        DroneController droneController = new DroneController(mockDroneRepository, mockStationRepository);
        Drone drone = new Drone();
        drone.setOperationStatus(OperationStatus.UDFASET);

        Mockito.when(mockDroneRepository.findById(drone_id)).thenReturn(Optional.of(drone));

        // Act
        droneController.enableDrone(1L);

        // Assert
        assert (drone.getOperationStatus() == OperationStatus.I_DRIFT);
        Mockito.verify(mockDroneRepository, Mockito.times(1)).save(drone);
    }

}
