package pjy.demo.domain.member.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pjy.demo.domain.member.entity.Member;
import pjy.demo.domain.member.repository.MemberRepository;
import pjy.demo.global.error.exception.ApiException;
import pjy.demo.global.error.exception.SystemError;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        validationDuplicationMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validationDuplicationMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new ApiException(SystemError.MEMBER_CONFLICT_ERROR);
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
