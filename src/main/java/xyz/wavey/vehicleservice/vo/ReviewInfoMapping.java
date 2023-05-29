package xyz.wavey.vehicleservice.vo;

import java.time.LocalDateTime;
import xyz.wavey.vehicleservice.model.Type;

public interface ReviewInfoMapping {
    Type getType();
    String getContent();
    String getNickName();
    String getProfile();
    LocalDateTime getCreateDate();



}
