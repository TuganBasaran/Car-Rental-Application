package cs_393_TZS.car_rental_application.service.impl;

import cs_393_TZS.car_rental_application.model.Member;
import cs_393_TZS.car_rental_application.repository.MemberRepository;
import cs_393_TZS.car_rental_application.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public Member registerMember(Member member) {
        return null;
    }

    @Override
    public Member updateMember(Long id, Member member) {
        return null;
    }

    @Override
    public Member getMemberById(Long id) {
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        return List.of();
    }

    @Override
    public void deleteMember(Long id) {

    }
}
