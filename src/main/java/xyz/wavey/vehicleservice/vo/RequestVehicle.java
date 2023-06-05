package xyz.wavey.vehicleservice.vo;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestVehicle {

    private Map<String, Object> feature;
    private String number;
    private Boolean available;
    private Integer charge;
    private Long lastZone;
    private Long frameId;
    private LocalDateTime washTime;
    private Integer mileage;
}
