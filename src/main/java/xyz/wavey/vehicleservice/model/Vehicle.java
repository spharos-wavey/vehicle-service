package xyz.wavey.vehicleservice.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import xyz.wavey.vehicleservice.base.BaseTimeEntity;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frame_id")
    private Frame frame;

    @Column(name = "color", nullable = false)
    private String color;

    //todo 아래의 타입 확인하기
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> feature = new HashMap<>();

    @Column(name = "latitude", precision = 15, scale = 12, nullable = false)
    private BigDecimal latitude;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "longitude", precision = 15, scale = 12, nullable = false)
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
    private Long lastZone;

    @Column(name = "wash_time", nullable = false)
    private Date washTime;
}