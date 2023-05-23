package xyz.wavey.vehicleservice.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseSummary {

    private String imageUrl;

    private String brandName;

    private String carName;
}
