package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    //Test object: Member
    String name = "Zehra";
    String address = "Tantavi";
    String mail = "zehrasagin@example.com";
    String phoneNumber = "12345";
    String drivingLicenseNumber = "12345";

    Member testMember = new Member(name, address, mail, phoneNumber, drivingLicenseNumber);



    @Test
    void saveMemberAndFindMemberById() {
        Member savedMember = memberService.saveMember(testMember);
        Optional<Member> foundMember = memberService.findMemberById(savedMember.getId());
        assertEquals(savedMember.getName(), foundMember.get().getName());
    }

    @Test
    void findMemberById() {
        Member savedMember = memberService.saveMember(testMember);
        Optional<Member> foundMember = memberService.findMemberById(savedMember.getId());
        assertEquals(savedMember.getName(), foundMember.get().getName());
    }

    @Test
    void deleteMemberById() {
        Member savedMember = memberService.saveMember(testMember);
        memberService.deleteMemberById(savedMember.getId());
        Optional<Member> foundMembers = memberService.findMemberById(savedMember.getId());
        assertTrue(foundMembers.isEmpty());
    }

    @AfterEach
    void deleteAll(){
        memberService.deleteAll();
    }
}