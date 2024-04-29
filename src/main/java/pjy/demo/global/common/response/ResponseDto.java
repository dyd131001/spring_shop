package pjy.demo.global.common.response;

import lombok.Builder;
import lombok.Getter;

/*
 사용 예시
   List<Member> members = MemberService.findAll();

   HttpHeaders headers= new HttpHeaders();
   headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

   return ResponseEntity
                .header(headers)
                .ok()
                .body(ResponseDto.of(members, "모든 유저 조회"));
 */
@Getter
@Builder
public class ResponseDto<T> {
    private final Boolean success;
    private final String category;
    private final String message;
    private final T data;

    public static <T> ResponseDto<T> of(T data , String message){
        return ResponseDto.<T>builder()
                .success(true)
                .category("OK")
                .message(message) // 수행 기능 적기
                .data(data)
                .build();
    }

    public static <T> ResponseDto<T> of(T data){
        return ResponseDto.<T>builder()
                .success(true)
                .category("OK")
                .message("") // 수행 기능 적기
                .data(data)
                .build();
    }



}
