package pjy.demo.global.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pjy.demo.domain.member.repository.JpaMemberRepository;
import pjy.demo.domain.member.repository.MemberRepository;
import pjy.demo.domain.member.service.MemberService;

@Configuration
public class SpringConfig {

    @PersistenceContext
    private EntityManager em;


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }
}
