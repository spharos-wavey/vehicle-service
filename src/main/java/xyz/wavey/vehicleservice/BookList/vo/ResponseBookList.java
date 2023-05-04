package xyz.wavey.vehicleservice.BookList.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.wavey.vehicleservice.vehicle.model.Vehicle;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResponseBookList {

  private Date startDate;
  private Date endDate;
  private Vehicle vehicle;
}
