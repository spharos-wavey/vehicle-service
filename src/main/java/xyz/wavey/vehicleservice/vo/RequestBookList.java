package xyz.wavey.vehicleservice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBookList {
  private String startDate;
  private String endDate;
  private Long vehicleId;

}
