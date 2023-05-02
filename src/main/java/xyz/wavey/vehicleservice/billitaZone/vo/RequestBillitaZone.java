package xyz.wavey.vehicleservice.billitaZone.vo;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RequestBillitaZone {
  private BigDecimal latitude;

  private BigDecimal longitude;

  private String name;
}
