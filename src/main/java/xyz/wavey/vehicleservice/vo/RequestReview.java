package xyz.wavey.vehicleservice.vo;

import lombok.Getter;
import lombok.Setter;
import xyz.wavey.vehicleservice.model.Type;

@Getter
@Setter
public class RequestReview {
  private Type type;
  private String content;
  private String nickName;
  private String profile;
  private Long vehicleId;
}
