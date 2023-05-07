package xyz.wavey.vehicleservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "content")
    private String content;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "profile")
    private String profile;

}
