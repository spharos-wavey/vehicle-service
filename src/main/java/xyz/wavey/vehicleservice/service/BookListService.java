package xyz.wavey.vehicleservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vo.RequestBookList;
import xyz.wavey.vehicleservice.vo.ResponseBookList;

public interface BookListService {

    ResponseEntity<Object> addBook(RequestBookList requestBookList);

    ResponseBookList getBook(Long id);
}
