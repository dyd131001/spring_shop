package pjy.demo.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.FilterChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pjy.demo.domain.member.dto.MemberDTO;
import pjy.demo.domain.member.entity.Member;
import pjy.demo.domain.member.service.MemberService;
import pjy.demo.global.common.response.ErrorResponseDTO;
import pjy.demo.global.common.response.ResponseDTO;
import pjy.demo.global.error.exception.ApiErrorExample;
import pjy.demo.global.error.exception.CommonError;

@Tag(name="member" , description = "회원 API")
@RestController
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/new")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
//            @ApiResponse(responseCode = "409", description = "존재하는 아이디가 있습니다.", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
//            @ApiResponse(responseCode = "400", description = "validation 오류.", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
//    })
    @ApiErrorExample(CommonError.class)
    @Operation(summary = "회원 등록" , description = "이름을 이용하여 중복을 검증하고 회원을 추가합니다.")
    public ResponseEntity<ResponseDTO> create(@RequestBody MemberDTO memberDTO){
        log.info("request={}", memberDTO);
        Member member = Member.builder()
                .name(memberDTO.getMemberName())
                .build();
        memberService.join(member);

        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(member, "유저 등록"));
    }


//    @GetMapping(value = "/headers")
//    @ResponseBody
//    public String headerTest(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            HttpMethod httpMethod,
//            Locale locale,
//            @RequestHeader MultiValueMap<String, String> headerMap,
//            @RequestHeader("host") String host,
//            @CookieValue(value="myCookie", required = false) String cookie
//    ) {
//        log.info("request={}", request);
//        log.info("response={}", response);
//        log.info("httpMethod={}", httpMethod);
//        log.info("locale={}", locale);
//        log.info("headerMap={}", headerMap);
//        log.info("header host={}", host);
//        log.info("myCookie={}", cookie);
//
//        return "test";
//    }

}
