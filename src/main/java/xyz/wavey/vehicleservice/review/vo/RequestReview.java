package xyz.wavey.vehicleservice.review.vo;

import lombok.Getter;
import lombok.Setter;
import xyz.wavey.vehicleservice.review.model.Type;

@Getter
@Setter
public class RequestReview {
  private Type type;
  private String content;
  private String nickName;
  private String profile;
}
