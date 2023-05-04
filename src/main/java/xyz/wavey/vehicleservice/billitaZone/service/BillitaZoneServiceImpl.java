package xyz.wavey.vehicleservice.billitaZone.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.billitaZone.model.BillitaZone;
import xyz.wavey.vehicleservice.billitaZone.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.billitaZone.vo.RequestBillitaZone;
import xyz.wavey.vehicleservice.billitaZone.vo.ResponseBillitaZone;
import xyz.wavey.vehicleservice.billitaZone.vo.ResponseGetAllBillitaZone;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillitaZoneServiceImpl implements BillitaZoneService {
  private final BillitaZoneRepo billitaZoneRepo;

  @Override
  public ResponseEntity<Object> addBillitaZone(RequestBillitaZone requestBillitaZone) {
    BillitaZone billitaZone = billitaZoneRepo.save(BillitaZone.builder()
        .name(requestBillitaZone.getName())
        .latitude(requestBillitaZone.getLatitude())
        .longitude(requestBillitaZone.getLongitude())
        .build());
    return ResponseEntity.status(HttpStatus.OK).body(billitaZone);
  }

  @Override
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

        for (BillitaZone billitaZone: billitaZoneList) {
            returnValue.add(ResponseGetAllBillitaZone.builder()
                    .id(billitaZone.getId())
                    .name(billitaZone.getName())
                    .latitude(billitaZone.getLatitude())
                    .longitude(billitaZone.getLongitude())
                    .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
