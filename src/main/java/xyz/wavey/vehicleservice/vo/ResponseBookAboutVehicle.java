package xyz.wavey.vehicleservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseBookAboutVehicle {
    private String maker;
    private String name;
    private String capacity;
    private Integer defaultPrice;
    private Integer distancePrice;
    private Integer charge;
}
