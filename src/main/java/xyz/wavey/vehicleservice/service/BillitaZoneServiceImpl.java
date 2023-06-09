package xyz.wavey.vehicleservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.vo.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillitaZoneServiceImpl implements BillitaZoneService {

    private final BillitaZoneRepo billitaZoneRepo;
    private final KakaoOpenFeign kakaoOpenFeign;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Transactional(readOnly = false)
    public BillitaZone addBillitaZone(RequestBillitaZone requestBillitaZone) {
        return billitaZoneRepo.save(BillitaZone.builder()
            .name(requestBillitaZone.getName())
            .latitude(requestBillitaZone.getLatitude())
            .longitude(requestBillitaZone.getLongitude())
            .zoneAddress(requestBillitaZone.getZoneAddress())
            .build());
    }

    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<ResponseTimeFilter> timeFilter(String sDate, String eDate, String lat, String lng) {

        List<ResponseTimeFilter> returnValue = new ArrayList<>();

        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = LocalDateTime.parse(sDate, dateTimeFormatter);
            endDate = LocalDateTime.parse(eDate, dateTimeFormatter);
        } catch (Exception e) {
            throw new ServiceException(BAD_REQUEST_DATEFORMAT.getMessage(),
                BAD_REQUEST_DATEFORMAT.getHttpStatus());
        }

        ResponseKakaoCoord2Address responseKakaoCoord2Address =
            kakaoOpenFeign.KakaoCoord2Address(RequestKakaoCoord2Address.builder()
                .x(lng)
                .y(lat)
                .build());

        if (responseKakaoCoord2Address.getMeta().getTotal_count() == 0)
            return returnValue;

        List<DtoTimeFilter> dtoTimeFilterList = billitaZoneRepo.jpqlTest(
            responseKakaoCoord2Address.getDocuments().get(0).getAddress().getRegion_1depth_name(), startDate, endDate);

        for (DtoTimeFilter dtoTimeFilter : dtoTimeFilterList) {
            if (isDistanceReachedLimit(
                Double.parseDouble(lat), Double.parseDouble(lng),
                dtoTimeFilter.getBillitaZoneLat(), dtoTimeFilter.getBillitaZoneLng())) {
                returnValue.add(ResponseTimeFilter.builder()
                    .billitaZoneLat(dtoTimeFilter.getBillitaZoneLat())
                    .billitaZoneLng(dtoTimeFilter.getBillitaZoneLng())
                    .billitaZoneId(dtoTimeFilter.getBillitaZoneId())
                    .billitaZoneName(dtoTimeFilter.getBillitaZoneName())
                    .rentAbleAmount(dtoTimeFilter.getRentAbleAmount())
                    .build());
            }
        }

        return returnValue;
    }


    @Override
    @Transactional(readOnly = true)
    public List<BillitaZone> billitaZoneInLimitDistance(double lat, double lng) {
        List<BillitaZone> returnValue = new ArrayList<>();

        ResponseKakaoCoord2Address responseKakaoCoord2Address = kakaoOpenFeign.KakaoCoord2Address(
            RequestKakaoCoord2Address.builder()
                .x(String.valueOf(lng))
                .y(String.valueOf(lat))
                .build());

        List<BillitaZone> billitaZoneList =
            billitaZoneRepo.findAllByRegionName(
                responseKakaoCoord2Address.getDocuments().get(0).getAddress().getRegion_1depth_name());

        for (BillitaZone billitaZone : billitaZoneList) {
            // 10km
            if (isDistanceReachedLimit(
                lat, lng, billitaZone.getLatitude().doubleValue(), billitaZone.getLongitude().doubleValue())) {
                returnValue.add(billitaZone);
            }
        }

        return returnValue;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseGetNowBillita> getNowBillita(double lat, double lng) {
        List<ResponseGetNowBillita> returnValue = new ArrayList<>();

        List<ResponseGetNowBillitaList> vehicleList = billitaZoneRepo.billitaTimeFilterByDistance(lat, lng);
        for (ResponseGetNowBillitaList responseGetNowBillitaList : vehicleList) {
            returnValue.add(ResponseGetNowBillita.builder()
                .vehicleId(responseGetNowBillitaList.getVehicleId())
                .carName(responseGetNowBillitaList.getCarName())
                .carImage(responseGetNowBillitaList.getCarImage())
                .billitaZoneId(responseGetNowBillitaList.getBillitaZoneId())
                .billitaZoneName(responseGetNowBillitaList.getBillitaZoneName())
                .build());
        }

        return returnValue;
    }

    @Override
    public Boolean isDistanceReachedLimit(double lat1, double lng1, double lat2, double lng2) {
        double theta = lat1 - lat2;
        double dist = Math.sin(lat1 * Math.PI / 180.0)
            * Math.sin(lat2 * Math.PI / 180.0)
            + Math.cos(lat1 * Math.PI / 180.0)
            * Math.cos(lat2 * Math.PI / 180.0)
            * Math.cos(theta * Math.PI / 180.0);
        dist = Math.acos(dist);
        dist = dist * 180 / Math.PI;
        dist *= 60 * 1.1515 * 1609.344;

        // 10km
        return dist < 10000;
    }


}
