package xyz.wavey.vehicleservice.vo;

import lombok.Builder;

@Builder
public class ResponseTimeFilter {

    public Long vehicleId;
    public Boolean canBook;
}
