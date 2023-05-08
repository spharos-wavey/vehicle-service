package xyz.wavey.vehicleservice.vo;

import java.math.BigDecimal;
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
public class ResponseBillitaZone {
  private BigDecimal latitude;

  private BigDecimal longitude;

  private String name;
}
