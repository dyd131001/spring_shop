package pjy.demo.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberError implements ErrorCode{

    MEMBER_CONFLICT_ERROR(HttpStatus.CONFLICT, "중복된 회원이 존재합니다.");


    private final HttpStatus httpStatus;
    private final String message;
}