package xyz.wavey.vehicleservice.vo;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.wavey.vehicleservice.model.Vehicle;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResponseBookList {

  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Vehicle vehicle;
}
