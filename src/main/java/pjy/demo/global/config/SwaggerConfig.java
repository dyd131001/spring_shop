package pjy.demo.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.ErrorResponse;
import org.springframework.web.method.HandlerMethod;
import pjy.demo.global.common.response.ErrorResponseDTO;
import pjy.demo.global.error.exception.ApiErrorExample;
import pjy.demo.global.error.exception.ErrorCode;

// API 문서 페이지의 기본 설명만 작성
// 나머지 설정은 application.properties 에서 설정
//name, email, url, 외부 문서등을 추가할 수 있다.
@OpenAPIDefinition(
        info = @Info(title = "Shop Web",
                description = "Shop api명세",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        // 전역으로 관리할 데이터 모델 정의하기
                        //.addSchemas("token", Token.class) // User 데이터 모델 추가
                );
    }

    // 어노테이션에 클래스 정보를 읽음
    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            ApiErrorExample apiErrorExample =
                    handlerMethod.getMethodAnnotation(ApiErrorExample.class);
            // ApiErrorCodeExample 어노테이션 단 메소드 적용
            if (apiErrorExample != null) {
                generateErrorResponseExample(operation, apiErrorExample.value());
            }
            return operation;
        };
    }

    // 클래스에 정의된 enum 요소를 모두 읽어 ExampleHolder를 구성함.
    // codeㄹ로 정렬
    private void generateErrorResponseExample(Operation operation, Class<? extends ErrorCode> type) {
        ApiResponses responses = operation.getResponses();
        // 해당 이넘에 선언된 에러코드들의 목록을 가져옵니다.
        ErrorCode[] errorCodes = type.getEnumConstants();
        // 400, 401, 404 등 에러코드의 상태코드들로 리스트로 모읍니다.
        // 400 같은 상태코드에 여러 에러코드들이 있을 수 있습니다.
        Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
                Arrays.stream(errorCodes)
                        .map(errorCode -> {
                            return ExampleHolder.builder()
                                    .holder(getSwaggerExample(errorCode)) // 에러 코드 공용 포멧만들기
                                    .code(errorCode.getHttpStatus().value())
                                    .name(errorCode.name())
                                    .build();

                        })
                        .collect(Collectors.groupingBy(ExampleHolder::getCode));
        // response 객체들을 responses 에 넣습니다.
        addExamplesToResponses(responses, statusWithExampleHolders);
    }


    // EventHolder내의 Example 객체를 만들기 위해 사용
    private Example getSwaggerExample(ErrorCode errorCode) {
//ErrorResponse 는 클라이언트한 실제 응답하는 공통 에러 응답 객체입니다.
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.of(errorCode);
        Example example = new Example();
        example.setValue(errorResponseDTO);
        return example;
    }


    // 가장 중요한 부분
    // content 부분을 런타임시점에 세팅함
    // 미디어 타입은 String key, example 객체를 넣어 세팅
    // example은 object타입 value와 string description, summary 등 다양한 필드를 가짐.
    /*
     @ApiResponse(
               responseCode = "200",
               description = "이미 회원가입을 했던 유저인 경우",
               content = @Content(
                    schema = @Schema(implementation = AfterOauthResponse.class))
    */
    private void addExamplesToResponses(ApiResponses responses,
                                        Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
        statusWithExampleHolders.forEach(
                (status, v) -> {
                    Content content = new Content();
                    MediaType mediaType = new MediaType();
                    ApiResponse apiResponse = new ApiResponse();

                    v.forEach(
                            exampleHolder -> mediaType.addExamples(
                                    exampleHolder.getName(),
                                    exampleHolder.getHolder()
                            )
                    );
                    content.addMediaType("application/json", mediaType);
                    apiResponse.setContent(content);
                    responses.addApiResponse(String.valueOf(status), apiResponse);
                }
        );

    }
    @Getter
    @Builder
    public static class ExampleHolder {
        // 스웨거의 Example 객체입니다. 위 스웨거 분석의 Example Object 참고.
        private Example holder;
        private String name;
        private int code;
    }


}

