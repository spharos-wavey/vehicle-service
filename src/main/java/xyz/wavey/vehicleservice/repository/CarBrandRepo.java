package xyz.wavey.vehicleservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.wavey.vehicleservice.model.CarBrand;
import xyz.wavey.vehicleservice.vo.DtoFindAllByFrameId;

public interface CarBrandRepo extends JpaRepository<CarBrand, Integer> {

    @Query(value =
        "SELECT vehi.id as vehicleId, vehi.charge as charge, f.image as imageUrl, f.car_name as carName, bz.name as billitaZone, f.default_price as defaultPrice, f.distance_price as distancePrice, cb.brand_name as carBrandName, bz.zone_address as zoneAddress, ( "
            +
            "6371 * acos (cos(radians(:lat)) " +
            "* cos(radians(latitude)) " +
            "* cos(radians(longitude) - radians(:lng)) " +
            "+ sin (radians(:lat)) * sin(radians(latitude)) " +
            ") " +
            ") AS distance " +
            "FROM " +
            "(SELECT * " +
            "FROM vehicle_db.vehicle veh " +
            "WHERE veh.id " +
            "NOT IN ( " +
            "SELECT bl.vehicle_id " +
            "FROM vehicle_db.book_list bl, vehicle_db.vehicle ve " +
            "WHERE bl.vehicle_id = ve.id " +
            "AND bl.end_date >= NOW() " +
            "AND bl.start_date <= DATE_ADD(NOW(), INTERVAL 2 HOUR))) as vehi JOIN billita_zone as bz " +
            "ON vehi.last_zone_id = bz.id join frame as f on vehi.frame_id = f.id " +
            "join car_brand as cb " +
            "on f.car_brand_id = cb.id && cb.id = :id " +
            "HAVING distance < 10 " +
            "ORDER BY distance ", nativeQuery = true)
    Slice<DtoFindAllByFrameId> findAllByFrameId(@Param("id") Integer id,
        @Param("lat") Double lat, @Param("lng") Double lng, Pageable pageable);
}
