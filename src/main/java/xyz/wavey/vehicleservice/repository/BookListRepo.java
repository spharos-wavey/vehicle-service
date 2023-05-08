package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.BookList;

import java.util.List;

public interface BookListRepo extends JpaRepository<BookList, Long> {

    List<BookList> findAllByVehicleIdOrderByStartDate(Long vehicleId);
}
