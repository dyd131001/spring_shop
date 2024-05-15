package pjy.demo.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Schema(description = "회원 DTO")
public class MemberDTO {

    @JsonProperty("member_name")
    @Schema(description = "회원 이름", defaultValue = "기본 이름"  /* allowableValues = { "", "" } */)
    private String memberName;

}
