package xyz.wavey.vehicleservice.vehicle.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
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

public class ResponseVehicle {
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
  private Long frameId;
  private Date washTime;
  private Map<String, Object> place;

}
