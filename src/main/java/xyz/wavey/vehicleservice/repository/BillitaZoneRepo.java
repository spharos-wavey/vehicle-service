package xyz.wavey.vehicleservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.vo.ZoneMapping;

public interface BillitaZoneRepo extends JpaRepository<BillitaZone, Long> {

    List<ZoneMapping> findAllById(Long id);
}
