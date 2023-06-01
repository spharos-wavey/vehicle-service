package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.vo.DtoTimeFilter;

import java.time.LocalDateTime;
import java.util.List;
import xyz.wavey.vehicleservice.vo.ResponseGetNowBillitaList;

public interface BillitaZoneRepo extends JpaRepository<BillitaZone, Long> {

    List<BillitaZone> findAllByRegionName(String regionName);

    @Query(value =
        "select l.id as billitaZoneId, l.latitude as billitaZoneLat, l.longitude as billitaZoneLng, l.c - ifnull(r.c, 0) as rentAbleAmount, l.name as billitaZoneName "
            +
            "from " +
            "(select b.*, count(v.id) as c " +
            "from vehicle_db.vehicle v " +
            "left join (select * from vehicle_db.billita_zone b where b.region_name = :regionName) b " +
            "on v.last_zone_id = b.id " +
            "    where b.id is not null " +
            "group by b.id) l " +
            "left join " +
            "(select v.last_zone_id, count(v.id) as c " +
            "from vehicle_db.vehicle v " +
            "left join vehicle_db.book_list b " +
            "on v.id = b.vehicle_id " +
            "where b.start_date < :eDate and b.end_date > :sDate " +
            "group by v.last_zone_id) r " +
            "on l.id = r.last_zone_id", nativeQuery = true)
    List<DtoTimeFilter> jpqlTest(@Param("regionName") String regionName, @Param("sDate") LocalDateTime sDate,
        @Param("eDate") LocalDateTime eDate);

    @Query(value =
        "SELECT vehi.id as vehicleId, f.image as carImage, f.car_name as carName, bz.name as billitaZoneName, vehi.last_zone_id as billitaZoneId, ( "
            +
            "6371 * acos (cos(radians(35.1678779)) " +
            "* cos(radians(latitude )) " +
            "* cos(radians(longitude) - radians(129.1231357)) " +
            "+ sin(radians(35.1678779)) * sin(radians(latitude)) " +
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
            "ON vehi.last_zone_id = bz.id left join frame as f on vehi.frame_id = f.id " +
            "HAVING distance < 10 " +
            "ORDER BY distance " +
            "LIMIT 20", nativeQuery = true)
    List<ResponseGetNowBillitaList> billitaTimeFilterByDistance(@Param("lat") Double lat, @Param("lng") Double lng);

}
