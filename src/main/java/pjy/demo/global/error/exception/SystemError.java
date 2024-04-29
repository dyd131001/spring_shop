package pjy.demo.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SystemError implements ErrorCode{

    UNCHECKED_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "에러 확인이 필요합니다."),
    MEMBER_CONFLICT_ERROR(HttpStatus.CONFLICT, "중복된 회원이 존재합니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
