package xyz.wavey.vehicleservice.billitaZone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.billitaZone.model.BillitaZone;

public interface BillitaZoneRepo extends JpaRepository<BillitaZone, Long> {

}
