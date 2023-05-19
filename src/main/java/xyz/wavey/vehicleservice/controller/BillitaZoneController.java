package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.vehicleservice.service.BillitaZoneService;
import xyz.wavey.vehicleservice.vo.RequestBillitaZone;
import xyz.wavey.vehicleservice.vo.ResponseBillitaZone;

@RestController
@RequestMapping("/billitazone")
@RequiredArgsConstructor
public class BillitaZoneController {
    private final BillitaZoneService billitaZoneService;

    @PostMapping()
    public ResponseEntity<Object> addBillitaZone(@RequestBody RequestBillitaZone requestBillitaZone) {
        return billitaZoneService.addBillitaZone(requestBillitaZone);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllBillitaZone() {
        return billitaZoneService.getAllBillitaZone();
    }

    @GetMapping("/{id}")
    public ResponseBillitaZone getBillitaZone(@PathVariable Long id) {
        return billitaZoneService.getBillitaZone(id);
    }

//    @GetMapping("/filter")
//    public ResponseEntity<Object> vehicleTimeFilter(
//            @RequestParam("sDate") String startDate,
//            @RequestParam("eDate") String endDate,
//            @RequestParam("lat") String lat,
//            @RequestParam("lng") String lng) {
//        return billitaZoneService.timeFilter(startDate, endDate, Double.parseDouble(lat), Double.parseDouble(lng));
//    }
}
