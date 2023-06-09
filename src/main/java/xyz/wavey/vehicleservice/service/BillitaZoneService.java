package xyz.wavey.vehicleservice.service;

import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.vo.*;

import java.util.List;

public interface BillitaZoneService {

    BillitaZone addBillitaZone(RequestBillitaZone requestBillitaZone);

    ResponseBillitaZone getBillitaZone(Long id);

    List<ResponseGetAllBillitaZone> getAllBillitaZone();

    // 예약 시간과 사용자의 위경도가 주어졌을 때 빌리타존별 이용가능한 차량을 조회하는 서비스
    List<ResponseTimeFilter> timeFilter(String sDate, String eDate, String lat, String lng);

    // 현재 사용자의 위경도가 주어졌을 때 반경 10km 이내의 빌리타존을 조회하는 서비스
    List<BillitaZone> billitaZoneInLimitDistance(double lat, double lng);

    List<ResponseGetNowBillita> getNowBillita(double lat, double lng);

    Boolean isDistanceReachedLimit(double lat1, double lng1, double lat2, double lng2);

}
