package xyz.wavey.vehicleservice.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBookList {
  private Date startDate;
  private Date endDate;
  private Long vehicleId;
}
