package xyz.wavey.vehicleservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetAllVehicleByMaker {

    private String carModel;
    private String imageUrl;

}
