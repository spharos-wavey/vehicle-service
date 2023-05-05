package xyz.wavey.vehicleservice.BookList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.BookList.model.BookList;

import java.util.List;

public interface BookListRepo extends JpaRepository<BookList, Long> {

    List<BookList> findAllByVehicleIdOrderByStartDate(Long vehicleId);
}
