package xyz.wavey.vehicleservice.billitaZone.vo;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class ResponseGetAllBillitaZone {

    public Long id;
    public String name;
    public BigDecimal latitude;
    public BigDecimal longitude;

}
