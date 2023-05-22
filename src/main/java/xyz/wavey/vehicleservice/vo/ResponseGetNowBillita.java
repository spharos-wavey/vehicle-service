package xyz.wavey.vehicleservice.vo;

import lombok.Builder;

@Builder
public class ResponseGetNowBillita {

    private Long vehicleId;
    private Long vehicleLastZone;
    private String frameName;
    private String frameImage;

}
