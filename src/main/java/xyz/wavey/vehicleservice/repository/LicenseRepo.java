package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.License;

public interface LicenseRepo extends JpaRepository<License, Integer> {

    License findByLicenseNumber(String licenseNumber);
    boolean existsByLicenseNumber(String licenseNumber);

}
