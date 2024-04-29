package pjy.demo.domain.member.repository;

import java.util.List;
import java.util.Optional;
import pjy.demo.domain.member.entity.Member;

public interface MemberRepository {
    public Member save(Member member);
    public Optional<Member> findById(Long id);
    public Optional<Member> findByName(String name);
    public List<Member> findAll();
}
