package xyz.wavey.vehicleservice.billitaZone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.BookList.model.BookList;
import xyz.wavey.vehicleservice.BookList.repository.BookListRepo;
import xyz.wavey.vehicleservice.billitaZone.model.BillitaZone;
import xyz.wavey.vehicleservice.billitaZone.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.billitaZone.vo.RequestBillitaZone;
import xyz.wavey.vehicleservice.billitaZone.vo.ResponseBillitaZone;
import xyz.wavey.vehicleservice.vehicle.model.Vehicle;
import xyz.wavey.vehicleservice.vehicle.repository.VehicleRepo;
import xyz.wavey.vehicleservice.billitaZone.vo.ResponseTimeFilter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillitaZoneServiceImpl implements BillitaZoneService {

    private final BillitaZoneRepo billitaZoneRepo;
    private final VehicleRepo vehicleRepo;
    private final BookListRepo bookListRepo;

    public ResponseEntity<Object> addBillitaZone(RequestBillitaZone requestBillitaZone) {
    BillitaZone billitaZone = billitaZoneRepo.save(BillitaZone.builder()
        .name(requestBillitaZone.getName())
        .latitude(requestBillitaZone.getLatitude())
        .longitude(requestBillitaZone.getLongitude())
        .build());
    return ResponseEntity.status(HttpStatus.OK).body(billitaZone);
    }

    public ResponseBillitaZone getBillitaZone(Long id) {
    BillitaZone billitaZone = billitaZoneRepo.findById(id).orElseThrow(() -> new ServiceException("error"));
    return ResponseBillitaZone.builder()
        .latitude(billitaZone.getLatitude())
        .longitude(billitaZone.getLongitude())
        .name(billitaZone.getName())
        .build();
    }

    public ResponseEntity<Object> vehicleTimeFilter(Long billitaZoneId, String sDate, String eDate) {
        //todo 프론트와 얘기하여 날짜 받는 포맷 확정
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");

        Date startDate;
        Date endDate;
        try {
            startDate = formatter.parse(sDate);
            endDate = formatter.parse(eDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<ResponseTimeFilter> returnValue = new ArrayList<>();

        // 해당 빌리타존에 있는 차량들을 모두 조회한다.
        List<Vehicle> vehiclesInBillitaZone = vehicleRepo.findAllByLastZone(billitaZoneId);
        for (Vehicle vehicle: vehiclesInBillitaZone) {
            // 차량 id를 이용하여 해당 차량이 startDate, endDate 사이에 예약되어 있는지를 조회한다.
            List<BookList> bookLists = bookListRepo.findAllByVehicleIdAndStartDateBetween(vehicle.getId(), startDate, endDate);
            boolean canBook = bookLists.isEmpty();
            returnValue.add(ResponseTimeFilter.builder()
                    .vehicleId(vehicle.getId())
                    .canBook(canBook)
                    .build());
        }

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

}
