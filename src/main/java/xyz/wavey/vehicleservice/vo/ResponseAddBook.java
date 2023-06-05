package xyz.wavey.vehicleservice.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ResponseAddBook {

    private Long bookId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long vehicleId;

}
