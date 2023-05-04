package xyz.wavey.vehicleservice.billitaZone.vo;

import lombok.Builder;

@Builder
public class ResponseTimeFilter {

    public Long vehicleId;
    public Boolean canBook;
}
