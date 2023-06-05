package xyz.wavey.vehicleservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vo.*;

public interface BookListService {

    ResponseAddBook addBook(RequestBookList requestBookList);

    ResponseBookList getBook(Long id);

    ResponseEntity<Object> deleteBook(Long id);

    ResponseBookAboutVehicle getBookAboutVehicle(Long id);

    ResponseSummary getSummary(Long vehicleId);

    boolean checkLicense(RequestCheckLicense requestCheckLicense);
}
