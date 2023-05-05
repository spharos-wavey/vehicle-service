package xyz.wavey.vehicleservice.billitaZone.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.billitaZone.vo.RequestBillitaZone;
import xyz.wavey.vehicleservice.billitaZone.vo.ResponseBillitaZone;

public interface BillitaZoneService {

    ResponseEntity<Object> addBillitaZone(RequestBillitaZone requestBillitaZone);

    ResponseBillitaZone getBillitaZone(Long id);

  ResponseEntity<Object> getAllBillitaZone();

    ResponseEntity<Object> vehicleTimeFilter(Long billitaZoneId, String startDate, String endDate);
}
