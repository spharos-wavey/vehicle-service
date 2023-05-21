package xyz.wavey.vehicleservice.vo;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBookList {
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Long vehicleId;
}
