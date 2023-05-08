package xyz.wavey.vehicleservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vo.RequestBillitaZone;
import xyz.wavey.vehicleservice.vo.ResponseBillitaZone;

public interface BillitaZoneService {

    ResponseEntity<Object> addBillitaZone(RequestBillitaZone requestBillitaZone);

    ResponseBillitaZone getBillitaZone(Long id);

    ResponseEntity<Object> getAllBillitaZone();

    ResponseEntity<Object> vehicleTimeFilter(Long billitaZoneId, String startDate, String endDate);
}
