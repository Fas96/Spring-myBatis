package com.argonet.hellospring.service;

import com.argonet.hellospring.domain.Member;
import com.argonet.hellospring.repository.MemberRepository;
import com.argonet.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {
    private final MemberRepository memoryMemberRepository;

    public MemberService(MemberRepository memoryMemberRepository) {
        this.memoryMemberRepository = memoryMemberRepository;
    }

    /*
        Member Registration
        * */
    public  Long join(Member member){
        System.out.println("firirisisdis------------------------");
        validateIfMemmberAvailable(member);


        memoryMemberRepository.save(member);

        System.out.println(member.getName()+"-------------------afer");
        return member.getId();
    }

    private void validateIfMemmberAvailable(Member member) {
        System.out.println(member.getName()+"-------------------aaaaaaaa");
        memoryMemberRepository.findByName(member.getName())
          .ifPresent(m->{
            throw new IllegalStateException("This member already exist");
          });

        System.out.println(member.getName()+"-------------------Zzzzzzzzzzzzzz");
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
