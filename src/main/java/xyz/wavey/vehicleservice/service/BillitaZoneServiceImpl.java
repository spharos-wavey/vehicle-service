package xyz.wavey.vehicleservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.model.BookList;
import xyz.wavey.vehicleservice.repository.BookListRepo;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.vo.RequestBillitaZone;
import xyz.wavey.vehicleservice.vo.ResponseBillitaZone;
import xyz.wavey.vehicleservice.vo.ResponseGetAllBillitaZone;

import java.util.ArrayList;

import xyz.wavey.vehicleservice.model.Vehicle;
import xyz.wavey.vehicleservice.repository.VehicleRepo;
import xyz.wavey.vehicleservice.vo.ResponseTimeFilter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Override
    public ResponseEntity<Object> getAllBillitaZone() {
        List<ResponseGetAllBillitaZone> returnValue = new ArrayList<>();
        List<BillitaZone> billitaZoneList = billitaZoneRepo.findAll();

        for (BillitaZone billitaZone : billitaZoneList) {
            returnValue.add(ResponseGetAllBillitaZone.builder()
                .id(billitaZone.getId())
                .name(billitaZone.getName())
                .latitude(billitaZone.getLatitude())
                .longitude(billitaZone.getLongitude())
                .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
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
        for (Vehicle vehicle : vehiclesInBillitaZone) {
            // 해당 차량의 모든 예약내용을 조회한다.
            List<BookList> bookLists = bookListRepo.findAllByVehicleIdOrderByStartDate(vehicle.getId());
            boolean canBook = true;
            for (BookList bookList : bookLists) {
                // 예약 테이블에서 예약 시작시간을 기준으로 오름차순 정렬했으므로 예약 시작시간이 현재 요청으로 들어온 예약 종료시간 보다 뒤에 있는 경우 비교를 하지 않아도 됨
                if (bookList.getStartDate().compareTo(endDate) > 0) {
                    break;
                }

                if (bookList.getEndDate().compareTo(startDate) > 0 && endDate.compareTo(bookList.getStartDate()) > 0) {
                    canBook = false;
                    break;
                }
            }
            returnValue.add(ResponseTimeFilter.builder()
                .vehicleId(vehicle.getId())
                .canBook(canBook)
                .build());
        }

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

}
