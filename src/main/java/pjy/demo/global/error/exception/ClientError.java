package pjy.demo.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ClientError implements ErrorCode{

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included");

    private final HttpStatus httpStatus;
    private final String message;
}
