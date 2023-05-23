package xyz.wavey.vehicleservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetVehicleInBillitaZone {

    private String carName;
    private Long vehicleId;
    private Boolean canBook;
    private Integer defaultPrice;
    private String vehicleImage;
    private Integer distancePrice;
    private Integer currentCharge;

}
