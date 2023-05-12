package xyz.wavey.vehicleservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.model.BookList;
import xyz.wavey.vehicleservice.model.Vehicle;
import xyz.wavey.vehicleservice.repository.BookListRepo;
import xyz.wavey.vehicleservice.repository.VehicleRepo;
import xyz.wavey.vehicleservice.vo.RequestBookList;
import xyz.wavey.vehicleservice.vo.ResponseBookAboutVehicle;
import xyz.wavey.vehicleservice.vo.ResponseBookList;
import xyz.wavey.vehicleservice.base.exception.ServiceException;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class BookListServiceImpl implements BookListService {

    private final BookListRepo bookListRepo;
    private final VehicleRepo vehicleRepo;

    @Override
    public ResponseEntity<Object> addBook(RequestBookList requestBookList) {
        Vehicle vehicle = vehicleRepo.findById(requestBookList.getVehicleId()).orElseThrow(()
            -> new ServiceException(NOT_FOUND_VEHICLE.getMessage(),
            NOT_FOUND_VEHICLE.getHttpStatus()));
        BookList bookList = bookListRepo.save(BookList.builder()
            .startDate(requestBookList.getStartDate())
            .endDate(requestBookList.getEndDate())
            .vehicle(vehicle)
            .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookList);
    }

    @Override
    public ResponseBookList getBook(Long id) {
        BookList bookList = bookListRepo.findById(id).orElseThrow(()
            -> new ServiceException(NOT_FOUND_BOOKLIST.getMessage(),
            NOT_FOUND_BOOKLIST.getHttpStatus()));
        return ResponseBookList.builder()
            .startDate(bookList.getStartDate())
            .endDate(bookList.getEndDate())
            .vehicle(bookList.getVehicle())
            .build();
    }

    @Override
    public ResponseEntity<Object> deleteBook(Long id) {
        BookList bookList = bookListRepo.findById(id).orElseThrow(()
            -> new ServiceException(NOT_FOUND_BOOKLIST.getMessage(),
            NOT_FOUND_BOOKLIST.getHttpStatus()));
        bookListRepo.delete(bookList);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseBookAboutVehicle getBookAboutVehicle(Long id) {
        BookList bookList = bookListRepo.findById(id).orElseThrow(()
            -> new ServiceException(NOT_FOUND_BOOKLIST.getMessage(),
            NOT_FOUND_BOOKLIST.getHttpStatus()));
        return ResponseBookAboutVehicle.builder()
            .defaultPrice(bookList.getVehicle().getFrame().getDefaultPrice())
            .distancePrice(bookList.getVehicle().getFrame().getDefaultPrice())
            .capacity(bookList.getVehicle().getFrame().getCapacity())
            .name(bookList.getVehicle().getFrame().getName())
            .maker(bookList.getVehicle().getFrame().getMaker())
            .charge(bookList.getVehicle().getCharge())
            .build();
    }
}
