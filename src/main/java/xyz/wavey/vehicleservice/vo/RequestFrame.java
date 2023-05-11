package xyz.wavey.vehicleservice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFrame {

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
    private String color;
    private String image;
}
