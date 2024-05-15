package pjy.demo.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonError implements ErrorCode{
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    UNCHECKED_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "에러 확인이 필요합니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
