package com.argonet.hellospring.service;

import com.argonet.hellospring.domain.Member;
import com.argonet.hellospring.repository.MemberRepository;
import com.argonet.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
    private final MemoryMemberRepository memoryMemberRepository;

    public MemberService(MemoryMemberRepository memoryMemberRepository) {
        this.memoryMemberRepository = memoryMemberRepository;
    }

    /*
        Member Registration
        * */
    public  Long join(Member member){

        validateIfMemmberAvailable(member);

        memoryMemberRepository.save(member);
        return member.getId();
    }

    private void validateIfMemmberAvailable(Member member) {
        memoryMemberRepository.findByName(member.getName())
          .ifPresent(m->{
            throw new IllegalStateException("This member already exist");
          });
    }

    /*ALl members listings*/
    public List<Member> findMembers(){
        return memoryMemberRepository.findAll();
    }

    /*find only one member */
    public Optional<Member> findOne(long memberId){
        return  memoryMemberRepository.findById(memberId);
    }
}
