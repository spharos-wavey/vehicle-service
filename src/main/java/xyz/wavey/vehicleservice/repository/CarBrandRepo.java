package xyz.wavey.vehicleservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.wavey.vehicleservice.model.CarBrand;
import xyz.wavey.vehicleservice.vo.DtoFindAllByFrameId;

public interface CarBrandRepo extends JpaRepository<CarBrand, Integer> {

    @Query(value = "select v.id as vehicleId, f.brand_name as carBrandName, f.car_name as carName, f.image as imageUrl, v.charge as charge, " +
            "f.default_price as defaultPrice, f.distance_price as distancePrice, bz.`name` as billitaZone, bz.zone_address as zoneAddress " +
            "from vehicle_db.vehicle v " +
            "right join (" +
            "select f.*, cb.brand_name " +
            "from vehicle_db.frame f " +
            "left join vehicle_db.car_brand cb " +
            "on f.car_brand_id = cb.id " +
            "where cb.id = :id) f " +
            "on v.frame_id = f.id " +
            "left join vehicle_db.billita_zone bz " +
            "on v.last_zone_id = bz.id;", nativeQuery = true)
    Slice<DtoFindAllByFrameId> findAllByFrameId(@Param("id") Integer id, Pageable pageable);

}
