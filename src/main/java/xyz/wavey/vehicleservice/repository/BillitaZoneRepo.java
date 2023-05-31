package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.vo.DtoTimeFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface BillitaZoneRepo extends JpaRepository<BillitaZone, Long> {

    List<BillitaZone> findAllByRegionName(String regionName);

    @Query(value = "select l.id as billitaZoneId, l.latitude as billitaZoneLat, l.longitude as billitaZoneLng, l.c - ifnull(r.c, 0) as rentAbleAmount, l.name as billitaZoneName " +
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
    List<DtoTimeFilter> jpqlTest(@Param("regionName") String regionName, @Param("sDate") LocalDateTime sDate, @Param("eDate") LocalDateTime eDate);

}
