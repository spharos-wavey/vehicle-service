package xyz.wavey.vehicleservice.frame.vo;

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
}
