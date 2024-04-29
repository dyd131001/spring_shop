package pjy.demo.domain.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import pjy.demo.domain.member.dto.MemberDTO;
import pjy.demo.domain.member.entity.Member;
import pjy.demo.domain.member.service.MemberService;
import pjy.demo.global.common.response.ResponseDto;

@Controller
@Slf4j
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/member/new")
    @ResponseBody
    public ResponseEntity<ResponseDto> create(MemberDTO memberDTO){
        Member member = Member.builder()
                .name(memberDTO.getName())
                .build();
        memberService.join(member);

        return ResponseEntity
                .ok()
                .body(ResponseDto.of(member, "유저 등록"));
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
