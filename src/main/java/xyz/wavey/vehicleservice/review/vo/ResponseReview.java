package xyz.wavey.vehicleservice.review.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.wavey.vehicleservice.review.model.Type;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseReview {
  private Type type;
  private String content;
  private String nickName;
  private String profile;
}
