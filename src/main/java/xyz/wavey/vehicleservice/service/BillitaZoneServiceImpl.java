package xyz.wavey.vehicleservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.repository.BookListRepo;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.vo.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import xyz.wavey.vehicleservice.repository.VehicleRepo;
import java.util.List;
import xyz.wavey.vehicleservice.model.Vehicle;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillitaZoneServiceImpl implements BillitaZoneService {

    private final BillitaZoneRepo billitaZoneRepo;
    private final VehicleRepo vehicleRepo;
    private final BookListRepo bookListRepo;

    public BillitaZone addBillitaZone(RequestBillitaZone requestBillitaZone) {
        return billitaZoneRepo.save(BillitaZone.builder()
            .name(requestBillitaZone.getName())
            .latitude(requestBillitaZone.getLatitude())
            .longitude(requestBillitaZone.getLongitude())
            .zoneAddress(requestBillitaZone.getZoneAddress())
            .build());
    }

    public ResponseBillitaZone getBillitaZone(Long id) {
        BillitaZone billitaZone = billitaZoneRepo.findById(id)
            .orElseThrow(() -> new ServiceException(NOT_FOUND_BILLITAZONE.getMessage(),
                NOT_FOUND_BILLITAZONE.getHttpStatus()));
        return ResponseBillitaZone.builder()
            .latitude(billitaZone.getLatitude())
            .longitude(billitaZone.getLongitude())
            .name(billitaZone.getName())
            .zoneAddress(billitaZone.getZoneAddress())
            .build();
    }

    @Override
    public List<ResponseGetAllBillitaZone> getAllBillitaZone() {
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
        return returnValue;
    }

    @Override
    public List<ResponseTimeFilter> timeFilter(String sDate, String eDate, double lat, double lng) {

        List<ResponseTimeFilter> returnValue = new ArrayList<>();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = LocalDateTime.parse(sDate, dateTimeFormatter);
            endDate = LocalDateTime.parse(eDate, dateTimeFormatter);
        } catch (Exception e) {
            throw new ServiceException(BAD_REQUEST_DATEFORMAT.getMessage(),
                BAD_REQUEST_DATEFORMAT.getHttpStatus());
        }

        for (BillitaZone billitaZone : billitaZoneInLimitDistance(lat, lng)) {
            int rentAbleAmount = 0;
            List<Vehicle> vehiclesInBillitaZone = vehicleRepo.findAllByLastZone(billitaZone);
            for (Vehicle vehicle : vehiclesInBillitaZone) {
                if (bookListRepo.timeFilter(vehicle.getId(), startDate, endDate).isEmpty()) {
                    rentAbleAmount++;
                }
            }

            returnValue.add(ResponseTimeFilter.builder()
                    .billitaZoneId(billitaZone.getId())
                    .billitaZoneName(billitaZone.getName())
                    .billitaZoneLat(billitaZone.getLatitude().doubleValue())
                    .billitaZoneLng(billitaZone.getLongitude().doubleValue())
                    .rentAbleAmount(rentAbleAmount)
                    .build());
        }

        return returnValue;
    }


    @Override
    public List<BillitaZone> billitaZoneInLimitDistance(double lat, double lng) {
        List<BillitaZone> returnValue = new ArrayList<>();

        List<BillitaZone> billitaZoneList = billitaZoneRepo.findAll();
        for (BillitaZone billitaZone : billitaZoneList) {
            double theta = lat - billitaZone.getLatitude().doubleValue();
            double dist = Math.sin(lat * Math.PI / 180.0)
                * Math.sin(billitaZone.getLatitude().doubleValue() * Math.PI / 180.0)
                + Math.cos(lat * Math.PI / 180.0)
                * Math.cos(billitaZone.getLatitude().doubleValue() * Math.PI / 180.0)
                * Math.cos(theta * Math.PI / 180.0);
            dist = Math.acos(dist);
            dist = dist * 180 / Math.PI;
            dist *= 60 * 1.1515 * 1609.344;

            // 10km
            if (dist < 10000) {
                returnValue.add(billitaZone);
            }
        }

        return returnValue;
    }

    @Override
    public List<ResponseGetNowBillita> getNowBillita(double lat, double lng) {
        List<ResponseGetNowBillita> returnValue = new ArrayList<>();

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime twoHoursLater = currentTime.plusHours(2);
        for (BillitaZone billitaZone : billitaZoneInLimitDistance(lat, lng)) {
            for (Vehicle vehicle : vehicleRepo.findAllByLastZone(billitaZone)) {
                if (bookListRepo.timeFilter(vehicle.getId(), currentTime, twoHoursLater)
                    .isEmpty()) {
                    returnValue.add(ResponseGetNowBillita.builder()
                        .vehicleId(vehicle.getId())
                        .billitaZoneId(vehicle.getLastZone().getId())
                        .billitaZoneName(vehicle.getLastZone().getName())
                        .carBrand(vehicle.getFrame().getCarBrand().getBrandName())
                        .carName(vehicle.getFrame().getCarName())
                        .carImage(vehicle.getFrame().getImage())
                        .build());
                }
            }
        }

        return returnValue;
    }
}
