package xyz.wavey.vehicleservice.billitaZone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wavey.vehicleservice.billitaZone.service.BillitaZoneService;
import xyz.wavey.vehicleservice.billitaZone.vo.RequestBillitaZone;
import xyz.wavey.vehicleservice.billitaZone.vo.ResponseBillitaZone;

@RestController
@RequestMapping("/billitazone")
@RequiredArgsConstructor
public class BillitaZoneController {
  private final BillitaZoneService billitaZoneService;

  @PostMapping()
  public ResponseEntity<Object> addBillitaZone(@RequestBody RequestBillitaZone requestBillitaZone){
    return billitaZoneService.addBillitaZone(requestBillitaZone);
  }

  @GetMapping("/{id}")
  public ResponseBillitaZone getBillitaZone(@PathVariable Long id){
    return billitaZoneService.getBillitaZone(id);
  }
}
