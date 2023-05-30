package xyz.wavey.vehicleservice.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestKakaoCoord2Address {

    private String x;
    private String y;

}
