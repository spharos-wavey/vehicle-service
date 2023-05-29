package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.wavey.vehicleservice.model.BookList;

import java.time.LocalDateTime;
import java.util.List;

public interface BookListRepo extends JpaRepository<BookList, Long> {

    @Query(value = "select b from BookList as b where b.vehicle.id = :id and b.endDate > :startDate and b.startDate < :endDate")
    List<BookList> timeFilter(@Param("id") Long id, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
