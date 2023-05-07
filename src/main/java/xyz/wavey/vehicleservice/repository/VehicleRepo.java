package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.Vehicle;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByLastZone(Long lastZoneId);
}
