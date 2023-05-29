package xyz.wavey.vehicleservice.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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

    private Map<String, Object> feature;
    private BillitaZone place;
    private Frame frameInfo;
    private String number;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean available;
    private Integer charge;
    private Long actualReturnedZone;
    private String smartKey;
    private LocalDateTime washTime;
    private Integer mileage;
    private List<ReviewInfoMapping> review;

}
