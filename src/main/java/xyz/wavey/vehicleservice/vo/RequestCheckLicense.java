package xyz.wavey.vehicleservice.vo;

import lombok.Data;

@Data
public class RequestCheckLicense {

    private String level;
    private String type;
    private String expireDate;
    private String issueDate;
    private String licenseNumber;
    private String address;
    private String addressDetail;
    private String birth;
    private String userName;

}
