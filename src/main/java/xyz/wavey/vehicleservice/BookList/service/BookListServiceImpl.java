package xyz.wavey.vehicleservice.BookList.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.BookList.model.BookList;
import xyz.wavey.vehicleservice.BookList.repository.BookListRepo;
import xyz.wavey.vehicleservice.BookList.vo.RequestBookList;
import xyz.wavey.vehicleservice.BookList.vo.ResponseBookList;

@Service
@RequiredArgsConstructor
public class BookListServiceImpl implements BookListService{
  private final BookListRepo bookListRepo;

  @Override
  public ResponseEntity<Object> addBook(RequestBookList requestBookList) {
    BookList bookList = bookListRepo.save(BookList.builder()
        .startDate(requestBookList.getStartDate())
        .endDate(requestBookList.getEndDate())
        .vehicle(requestBookList.getVehicle())
        .build());
    return ResponseEntity.status(HttpStatus.OK).body(bookList);
  }

  @Override
  public ResponseBookList getBook(Long id) {
    BookList bookList = bookListRepo.findById(id).orElseThrow(() -> new ServiceException("error"));
    return ResponseBookList.builder()
        .startDate(bookList.getStartDate())
        .endDate(bookList.getEndDate())
        .vehicle(bookList.getVehicle())
        .build();
  }
}
