package xyz.wavey.vehicleservice.vo;

import lombok.Builder;

@Builder
public class ResponseTimeFilter {

    public double billitaZoneLat;
    public double billitaZoneLng;
    public Long billitaZoneId;
    public Integer rentAbleAmount;
}
