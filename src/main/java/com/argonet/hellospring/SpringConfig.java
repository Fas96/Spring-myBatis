package com.argonet.hellospring;

import com.argonet.hellospring.repository.MemberRepository;
import com.argonet.hellospring.repository.MemoryMemberRepository;
import com.argonet.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
