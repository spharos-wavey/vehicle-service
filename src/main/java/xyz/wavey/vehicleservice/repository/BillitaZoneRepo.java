package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.BillitaZone;

import java.util.List;

public interface BillitaZoneRepo extends JpaRepository<BillitaZone, Long> {

    List<BillitaZone> findAllByRegionName(String regionName);

}
