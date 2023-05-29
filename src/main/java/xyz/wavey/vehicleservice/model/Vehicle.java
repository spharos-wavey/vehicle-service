package xyz.wavey.vehicleservice.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
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

    @ManyToOne
    @JoinColumn
    private Frame frame;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> feature;

    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal latitude;

    @Column(nullable = false)
    private String number;

    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal longitude;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private Integer charge;

    @Column
    private String smartKey;

    @OneToOne
    private BillitaZone lastZone;

    @Column(nullable = false)
    private LocalDateTime washTime;

    @Column(nullable = false)
    private Integer mileage;
}