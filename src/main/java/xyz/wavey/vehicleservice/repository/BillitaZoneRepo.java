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
        "SELECT V.id as vehicleId, image as carImage, car_name as carName, name as billitaZoneName, last_zone_id as billitaZoneId, ( "
            +
            "6371 * acos (cos(radians(35.1678779)) " +
            "* cos(radians(latitude )) " +
            "* cos(radians(longitude) - radians(129.1231357)) " +
            "+ sin(radians(35.1678779)) * sin(radians(latitude)) " +
            ") " +
            ") AS distance " +
            "FROM " +
            "(SELECT * " +
            "FROM vehicle " +
            "WHERE vehicle.id " +
            "NOT IN ( " +
            "SELECT vehicle_id " +
            "FROM book_list, vehicle " +
            "WHERE book_list.vehicle_id = vehicle_id " +
            "AND book_list.end_date >= NOW() " +
            "AND book_list.start_date <= DATE_ADD(NOW(), INTERVAL 2 HOUR))) as V JOIN billita_zone " +
            "ON V.last_zone_id = billita_zone.id left join frame on V.id = frame_id " +
            "HAVING distance < 10 " +
            "ORDER BY distance " +
            "LIMIT 20", nativeQuery = true)
    List<ResponseGetNowBillitaList> billitaTest(@Param("lat") Double lat, @Param("lng") Double lng);

}
