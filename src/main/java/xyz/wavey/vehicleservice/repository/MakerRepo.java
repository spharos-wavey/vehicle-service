package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.Maker;

public interface MakerRepo extends JpaRepository<Maker, Integer> {
}
