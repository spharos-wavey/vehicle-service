package xyz.wavey.vehicleservice.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.vehicle.model.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
}
