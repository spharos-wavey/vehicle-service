package xyz.wavey.vehicleservice.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.model.BookList;
import xyz.wavey.vehicleservice.model.Vehicle;
import xyz.wavey.vehicleservice.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.repository.BookListRepo;
import xyz.wavey.vehicleservice.repository.VehicleRepo;
import xyz.wavey.vehicleservice.vo.RequestBookList;
import xyz.wavey.vehicleservice.vo.ResponseBookAboutVehicle;
import xyz.wavey.vehicleservice.vo.ResponseBookList;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.vo.ZoneMapping;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class BookListServiceImpl implements BookListService {

    private final BookListRepo bookListRepo;
    private final VehicleRepo vehicleRepo;
    private final BillitaZoneRepo billitaZoneRepo;

    private DateTimeFormatter dateTimeFormatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter dateTimeFormatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");

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
    public ResponseEntity<Object> getBookAboutVehicle(Long id) {
        BookList bookList = bookListRepo.findById(id).orElseThrow(()
            -> new ServiceException(NOT_FOUND_BOOKLIST.getMessage(),
            NOT_FOUND_BOOKLIST.getHttpStatus()));

        List<ZoneMapping> billitazoneName = billitaZoneRepo.findAllById(id);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseBookAboutVehicle.builder()
                .rentId(bookList.getId())
                .defaultPrice(bookList.getVehicle().getFrame().getDefaultPrice())
                .distancePrice(bookList.getVehicle().getFrame().getDefaultPrice())
                .capacity(bookList.getVehicle().getFrame().getCapacity())
                .carModel(bookList.getVehicle().getFrame().getName())
                .maker(bookList.getVehicle().getFrame().getMaker())
                .charge(bookList.getVehicle().getCharge())
                .imageUrl(bookList.getVehicle().getFrame().getImage())
                .startTime(bookList.getStartDate().format(dateTimeFormatterTime))
                .startDate(bookList.getStartDate().format(dateTimeFormatterDate))
                .endDate(bookList.getEndDate().format(dateTimeFormatterDate))
                .endTime(bookList.getEndDate().format(dateTimeFormatterTime))
                .billitaZone(billitazoneName)
            .build());
    }
}
