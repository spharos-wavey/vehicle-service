package xyz.wavey.vehicleservice.BookList.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.BookList.vo.RequestBookList;
import xyz.wavey.vehicleservice.BookList.vo.ResponseBookList;

public interface BookListService {

  ResponseEntity<Object> addBook (RequestBookList requestBookList);

  ResponseBookList getBook(Long id);
}
