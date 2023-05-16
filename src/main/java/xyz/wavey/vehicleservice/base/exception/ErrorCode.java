package xyz.wavey.vehicleservice.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_VEHICLE("등록된 차량 정보가 존재하지 않습니다.", NOT_FOUND, 404),
    NOT_FOUND_BOOKLIST("등록된 예약 정보가 존재하지 않습니다.", NOT_FOUND, 404),
    NOT_FOUND_BILLITAZONE("등록된 빌리타존 정보가 존재하지 않습니다.", NOT_FOUND, 404),
    NOT_FOUND_FRAME("등록된 프레임 정보가 존재하지 않습니다.", NOT_FOUND, 404),
    NOT_FOUND_REVIEW("등록된 후기가 존재하지 않습니다.", NOT_FOUND, 404),

    BAD_REQUEST_DATEFORMAT("날짜 형식이 잘못되었습니다.", BAD_REQUEST, 400)
    ;
    private final String message;
    private final HttpStatus httpStatus;
    private final Integer errorCode;
}
