package xyz.wavey.vehicleservice.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.service.BillitaZoneService;
import xyz.wavey.vehicleservice.vo.RequestBillitaZone;
import xyz.wavey.vehicleservice.vo.ResponseGetAllBillitaZone;
import xyz.wavey.vehicleservice.vo.ResponseGetNowBillita;

@RestController
@RequestMapping("/billitazone")
@RequiredArgsConstructor
public class BillitaZoneController {

    private final BillitaZoneService billitaZoneService;

    @PostMapping()
    public ResponseEntity<Object> addBillitaZone(
        @RequestBody RequestBillitaZone requestBillitaZone) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(billitaZoneService.addBillitaZone(requestBillitaZone));
    }

    @GetMapping()
    public ResponseEntity<Object> getAllBillitaZone() {
        List<ResponseGetAllBillitaZone> billitaZoneList = billitaZoneService.getAllBillitaZone();

        if (billitaZoneList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                .body(billitaZoneList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBillitaZone(@PathVariable Long id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(billitaZoneService.getBillitaZone(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> vehicleTimeFilter(
        @RequestParam("sDate") String startDate,
        @RequestParam("eDate") String endDate,
        @RequestParam("lat") String lat,
        @RequestParam("lng") String lng) {
        return billitaZoneService.timeFilter(startDate, endDate, Double.parseDouble(lat),
            Double.parseDouble(lng));
    }

    @GetMapping("/now")
    public ResponseEntity<Object> getNowBillita(
        @RequestParam("lat") String lat,
        @RequestParam("lng") String lng) {

        List<ResponseGetNowBillita> responseGetNowBillitaList = billitaZoneService.getNowBillita(
            Double.parseDouble(lat), Double.parseDouble(lng));

        if (responseGetNowBillitaList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                .body(responseGetNowBillitaList);
        }
    }
}
