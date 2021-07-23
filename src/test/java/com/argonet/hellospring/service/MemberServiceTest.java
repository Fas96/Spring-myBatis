package com.argonet.hellospring.service;

import com.argonet.hellospring.domain.Member;
import com.argonet.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService  memberService ;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        this.memberService = new MemberService(memoryMemberRepository);
        this.memoryMemberRepository = new MemoryMemberRepository();
    }

    @AfterEach
    public void afterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hellow");
        //when
        Long savedId = memberService.join(member);
        //then
        Member findMembers = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(findMembers.getName());
    }
    @Test
    public void memberAvailableCheck(){
        Member member1 = new Member();
        member1.setName("hellow");

        Member member2 = new Member();
        member2.setName("hellow");

        memberService.join(member1);

        IllegalStateException expectedType =assertThrows(IllegalStateException.class,()->memberService.join(member2));
        assertThat(expectedType.getMessage()).isEqualTo("This member already exist");
    /*    try {
            memberService.join(member2);

        }catch (IllegalStateException  e){
            assertThat(e.getMessage()).isEqualTo("This member already exist");
        }
*/

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}