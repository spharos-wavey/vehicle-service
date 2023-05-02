package xyz.wavey.vehicleservice.frame.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.wavey.vehicleservice.baseTime.BaseTimeEntity;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Frame extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "maker", nullable = false)
  private String maker;

  @Column(name = "foreign_car", nullable = false)
  private Boolean foreignCar;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "capacity", nullable = false)
  private String capacity;

  @Column(name = "recommend")
  private Boolean recommend;

  @Column(name = "default_price", nullable = false)
  private Integer defaultPrice;

  @Column(name = "distance_price", nullable = false)
  private Integer distancePrice;
}
