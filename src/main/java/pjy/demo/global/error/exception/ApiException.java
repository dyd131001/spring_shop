package pjy.demo.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiException extends RuntimeException{
    private final ErrorCode errorCode;
}
