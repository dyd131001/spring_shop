package pjy.demo.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pjy.demo.global.common.response.ErrorResponseDto;
import pjy.demo.global.error.exception.ApiException;
import pjy.demo.global.error.exception.ClientError;
import pjy.demo.global.error.exception.ErrorCode;
import pjy.demo.global.error.exception.SystemError;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /*
    ApiException은 ErrorCode를 보관하고 있고
    ErrorCode를 이용하여 ResponseEntity<ErrorResponse>를 만들어야한다.

    필드의 경우 자동 초기화가 되기 때문에 validationErrorList를 만들때 .errors()를
    하지않는 경우 null인채로 ErrorResponse가 만들어짐.
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleCustomException(ApiException e){
        ErrorCode errorCode = e.getErrorCode();
        return handleExecptionInternal(errorCode);
    }

    private ResponseEntity<Object> handleExecptionInternal(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseDto.of(errorCode));
    }



    /*
    @valid 로 검증하는 객체가 오류시 handleMethodArgumentNotValid가 발생.
    해당 클래스가 extend하는 ResponseEntityExceptionHandler에서
    handleMethodArgumentNotValid 함수가 이를 핸들링함.
     */

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn("handleIllegalArgument", e);
        ErrorCode errorCode = ClientError.INVALID_PARAMETER;
        return handleExceptionInternal(e,errorCode);
    }

    private ResponseEntity<Object> handleExceptionInternal(MethodArgumentNotValidException e, ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseDto.of(e, errorCode));
    }



    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex) {
        log.warn("handleAllException", ex);
        ErrorCode errorCode = SystemError.UNCHECKED_SERVER_ERROR;
        return handleExecptionInternal(errorCode);
    }



}
