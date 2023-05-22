package xyz.wavey.vehicleservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.wavey.vehicleservice.base.BaseTimeEntity;

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

    @ManyToOne
    private Maker maker;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String capacity;

    @Column(nullable = false)
    private Boolean recommend;

    @Column(nullable = false)
    private Integer defaultPrice;

    @Column(nullable = false)
    private Integer distancePrice;

    @Column(nullable = false)
    private String carType;

    @Column(nullable = false)
    private String appearance;

    @Column(nullable = false)
    private String manual;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String image;
}
