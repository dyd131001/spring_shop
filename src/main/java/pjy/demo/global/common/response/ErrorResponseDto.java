package pjy.demo.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import pjy.demo.global.error.exception.ErrorCode;

/*
    예시

    throw new ApiException(ClientError.INVALID_PARAMETER);
 */
@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponseDto {

    private final boolean sucess;
    private final String category;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ValidationError> errors;

    public static ErrorResponseDto of(ErrorCode errorCode){
        return ErrorResponseDto.builder()
                .sucess(false)
                .category(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }

    public static ErrorResponseDto of(MethodArgumentNotValidException e, ErrorCode errorCode){
        List<ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponseDto.builder()
                .sucess(true)
                .category(errorCode.name())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {

        private final String field;
        private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }

}
