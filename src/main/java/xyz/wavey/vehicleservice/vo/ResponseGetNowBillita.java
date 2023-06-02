package xyz.wavey.vehicleservice.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseGetNowBillita {

    private Long vehicleId;
    private Long billitaZoneId;
    private String billitaZoneName;
    private String carName;
    private String carImage;

}
