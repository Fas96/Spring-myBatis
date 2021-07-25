package com.argonet.hellospring;

import com.argonet.hellospring.repository.JdbcTemplateMemberRepository;
import com.argonet.hellospring.repository.MemberRepository;
import com.argonet.hellospring.repository.MemoryMemberRepository;
import com.argonet.hellospring.repository.jdbcMemberRepository;
import com.argonet.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){

        //return new jdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
