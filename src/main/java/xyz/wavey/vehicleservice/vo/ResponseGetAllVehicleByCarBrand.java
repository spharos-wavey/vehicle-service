package xyz.wavey.vehicleservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetAllVehicleByCarBrand {

    private String carName;
    private String imageUrl;
    private Integer charge;
    private String carBrandName;
    private String zoneAddress;

}
