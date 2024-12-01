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

    public Member saveMember(Member member){
        return memberRepository.save(member);
    }


    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No member found with ID: " + id));
    }

    /*
    Optional<Member> findMemberById(Long id){
        try{
            return memberRepository.findById(id);
        } catch (NoSuchElementException noSuchElementException){
            throw new IllegalArgumentException("No such element");
        }

    }

     */

    public List<Member> findAllMembers(){
        return memberRepository.findAll();
    }


    public void deleteMemberById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("No such id: " + id);
        }
        memberRepository.deleteById(id);
    }

    public void deleteAll(){
        memberRepository.deleteAll();
    }
}
