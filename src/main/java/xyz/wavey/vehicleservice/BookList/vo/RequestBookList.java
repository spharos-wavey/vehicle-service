package xyz.wavey.vehicleservice.BookList.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import xyz.wavey.vehicleservice.vehicle.model.Vehicle;

@Getter
@Setter
public class RequestBookList {
  private Date startDate;
  private Date endDate;
  private Vehicle vehicle;
}
