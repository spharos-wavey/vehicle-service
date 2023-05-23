package xyz.wavey.vehicleservice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFrame {

    private Integer carBrandId;
    private String carName;
    private String capacity;
    private Boolean recommend;
    private Integer defaultPrice;
    private Integer distancePrice;
    private String carType;
    private String appearance;
    private String manual;
    private String color;
    private String image;
}
