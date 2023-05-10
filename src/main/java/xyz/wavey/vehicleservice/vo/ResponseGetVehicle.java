package xyz.wavey.vehicleservice.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.model.Frame;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ResponseGetVehicle {
    private String color;
    private Map<String, Object> feature;
    private String number;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean available;
    private Integer charge;
    private String image;
    private Long lastZone;
    private String smartKey;
    private Frame frameInfo;
    private Date washTime;
    private BillitaZone place;
    private Integer mileage;

}
