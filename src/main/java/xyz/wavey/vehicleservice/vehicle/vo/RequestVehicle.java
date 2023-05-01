package xyz.wavey.vehicleservice.vehicle.vo;

import java.math.BigDecimal;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestVehicle {
  private String color;
  private Map<String, Object> feature;
  private String number;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private Boolean available;
  private Integer charge;
  private String image;
  private Integer lastZone;
  private String smartKey;
}
