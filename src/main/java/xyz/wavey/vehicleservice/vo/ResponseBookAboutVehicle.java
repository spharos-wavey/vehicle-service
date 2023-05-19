package xyz.wavey.vehicleservice.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
public class ResponseBookAboutVehicle {
    private Long rentId;
    private String maker;
    private String carModel;
    private String capacity;
    private Integer defaultPrice;
    private Integer distancePrice;
    private Integer charge;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private List<ZoneMapping> billitaZone;
    private String imageUrl;
}
