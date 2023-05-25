package xyz.wavey.vehicleservice.service;

import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.model.BookList;
import xyz.wavey.vehicleservice.model.Vehicle;
import xyz.wavey.vehicleservice.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.repository.BookListRepo;
import xyz.wavey.vehicleservice.repository.VehicleRepo;
import xyz.wavey.vehicleservice.vo.RequestBookList;
import xyz.wavey.vehicleservice.vo.ResponseBookAboutVehicle;
import xyz.wavey.vehicleservice.vo.ResponseBookList;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.vo.ResponseSummary;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class BookListServiceImpl implements BookListService {

    private final BookListRepo bookListRepo;
    private final VehicleRepo vehicleRepo;
    private final BillitaZoneRepo billitaZoneRepo;

    private final DateTimeFormatter dateTimeFormatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");

    @Override
    public BookList addBook(RequestBookList requestBookList) {
        Vehicle vehicle = vehicleRepo.findById(requestBookList.getVehicleId()).orElseThrow(()
            -> new ServiceException(NOT_FOUND_VEHICLE.getMessage(),
            NOT_FOUND_VEHICLE.getHttpStatus()));
        return bookListRepo.save(BookList.builder()
            .startDate(requestBookList.getStartDate())
            .endDate(requestBookList.getEndDate())
            .vehicle(vehicle)
            .build());
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

        BillitaZone billitaZone = billitaZoneRepo.findById(id).orElseThrow(()
            -> new ServiceException(NOT_FOUND_BILLITAZONE.getMessage(),
            NOT_FOUND_BILLITAZONE.getHttpStatus()));

        return ResponseBookAboutVehicle.builder()
            .rentId(bookList.getId())
            .defaultPrice(bookList.getVehicle().getFrame().getDefaultPrice())
            .distancePrice(bookList.getVehicle().getFrame().getDefaultPrice())
            .capacity(bookList.getVehicle().getFrame().getCapacity())
            .carName(bookList.getVehicle().getFrame().getCarName())
            .carBrand(bookList.getVehicle().getFrame().getCarBrand().getBrandName())
            .charge(bookList.getVehicle().getCharge())
            .imageUrl(bookList.getVehicle().getFrame().getImage())
            .startDate(bookList.getStartDate().format(dateTimeFormatterDate))
            .endDate(bookList.getEndDate().format(dateTimeFormatterDate))
            .billitaZone(billitaZone.getName())
            .build();
    }

    @Override
    public ResponseSummary getSummary(Long vehicleId) {
        Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow(()
            -> new ServiceException(NOT_FOUND_VEHICLE.getMessage(),
            NOT_FOUND_VEHICLE.getHttpStatus()));

        return ResponseSummary.builder()
                .imageUrl(vehicle.getFrame().getImage())
                .brandName(vehicle.getFrame().getCarBrand().getBrandName())
                .carName(vehicle.getFrame().getCarName())
                .build();
    }
}
