package xyz.wavey.vehicleservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseFrame {

    private String maker;
    private Boolean foreignCar;
    private String name;
    private String capacity;
    private Boolean recommend;
    private Integer defaultPrice;
    private Integer distancePrice;
    private String carType;
    private String appearance;
    private String manuel;
}
