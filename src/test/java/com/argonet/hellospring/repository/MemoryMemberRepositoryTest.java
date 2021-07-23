package com.argonet.hellospring.repository;

import com.argonet.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MemoryMemberRepositoryTest  {
   MemoryMemberRepository repository = new MemoryMemberRepository();

   @AfterEach
   public void AfterEach(){
    repository.clearStore();
   }
   @Test
    public void save(){
       Member member = new Member();
       member.setName("Fas is here");

       repository.save(member);

       Member result = repository.findById(member.getId()).get();
       Assertions.assertThat(member).isEqualTo(result);
   }


    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Fas is two");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Fas is three");
        repository.save(member2);

        Member result = repository.findByName("Fas is two").get();
        Assertions.assertThat(member1).isEqualTo(result);
    }



}
