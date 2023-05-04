package xyz.wavey.vehicleservice.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum Type {
  REVIEW("리뷰"),
  INFORMATION("정보"),
  ;
  private String message;
}
