package xyz.wavey.vehicleservice.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.vehicleservice.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.service.BillitaZoneService;
import xyz.wavey.vehicleservice.service.KakaoOpenFeign;
import xyz.wavey.vehicleservice.vo.*;

@RestController
@RequestMapping("/billitazone")
@RequiredArgsConstructor
public class BillitaZoneController {

    private final BillitaZoneService billitaZoneService;
    private final BillitaZoneRepo billitaZoneRepo;
    private final KakaoOpenFeign kakaoOpenFeign;

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
        
        List<ResponseTimeFilter> responseTimeFilters = billitaZoneService.timeFilter(startDate, endDate, Double.parseDouble(lat), Double.parseDouble(lng));

        return ResponseEntity.status(HttpStatus.OK).body(responseTimeFilters);

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

    @GetMapping("/jqpl")
    public ResponseEntity<Object> jpqlTest(
            @RequestParam("sDate") String startDate,
            @RequestParam("eDate") String endDate,
            @RequestParam("lat") String lat,
            @RequestParam("lng") String lng) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        ResponseKakaoCoord2Address responseKakaoCoord2Address = kakaoOpenFeign.KakaoCoord2Address(RequestKakaoCoord2Address.builder()
                        .x(lng)
                        .y(lat)
                .build());
        return ResponseEntity.status(HttpStatus.OK).body(billitaZoneRepo.jpqlTest(responseKakaoCoord2Address.getDocuments().get(0).getAddress().getRegion_1depth_name()
                , LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter)));
    }

}
