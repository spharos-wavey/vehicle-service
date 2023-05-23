package xyz.wavey.vehicleservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vo.RequestBookList;
import xyz.wavey.vehicleservice.vo.ResponseBookList;
import xyz.wavey.vehicleservice.vo.ResponseSummary;

public interface BookListService {

    ResponseEntity<Object> addBook(RequestBookList requestBookList);

    ResponseBookList getBook(Long id);

    ResponseEntity<Object> deleteBook(Long id);

    ResponseEntity<Object> getBookAboutVehicle(Long id);

    ResponseSummary getSummary(Long vehicleId);
}
