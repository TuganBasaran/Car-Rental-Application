package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.Member;
import cs_393_TZS.car_rental_application.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    Member saveMember(Member member){
        return memberRepository.save(member);
    }

    Optional<Member> findMemberById(Long id){
        try{
            return memberRepository.findById(id);
        } catch (NoSuchElementException noSuchElementException){
            throw new IllegalArgumentException("No such element");
        }

    }

    void deleteMemberById(Long id){
        memberRepository.deleteById(id);
    }

    void deleteAll(){
        memberRepository.deleteAll();
    }
}
