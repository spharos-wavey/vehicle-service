package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.CarBrand;

public interface CarBrandRepo extends JpaRepository<CarBrand, Integer> {
}
