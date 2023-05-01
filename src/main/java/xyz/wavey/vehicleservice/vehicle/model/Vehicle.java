package xyz.wavey.vehicleservice.vehicle.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> feature = new HashMap<>();

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "charge", nullable = false)
    private Integer charge;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "smart_key", nullable = false)
    private String smartKey;

    @Column(name = "last_zone", nullable = false)
    private Integer lastZone;

}
