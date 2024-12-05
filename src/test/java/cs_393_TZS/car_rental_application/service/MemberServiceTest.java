package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.MemberDTO;
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

    MemberDTO generateTestObject(){
        return new MemberDTO(null, "Zehra", "Istanbul", "zehra123@example.com",
                "0123456", "A1B2C3");
    }

    @Test
    void toDTO() {
        Member member = new Member("Zehra", "Istanbul", "zehra123@example.com",
                "0123456", "A1B2C3");
        MemberDTO memberDTO = memberService.toDTO(member);
        assertEquals(member.getName(), memberDTO.getName());
        assertEquals(member.getEmail(), memberDTO.getEmail());
        assertEquals(member.getPhone(), memberDTO.getPhone());
        assertEquals(member.getDrivingLicenseNumber(), memberDTO.getDrivingLicenseNumber());
    }

    @Test
    void toEntity() {
        MemberDTO memberDTO = generateTestObject();
        Member member = memberService.toEntity(memberDTO);
        assertEquals(member.getName(), memberDTO.getName());
        assertEquals(member.getEmail(), memberDTO.getEmail());
        assertEquals(member.getPhone(), memberDTO.getPhone());
        assertEquals(member.getDrivingLicenseNumber(), memberDTO.getDrivingLicenseNumber());
    }

    @Test
    void saveMemberFindByID() {
        MemberDTO memberDTO = generateTestObject();
        MemberDTO savedMember = memberService.saveMember(memberDTO);

        MemberDTO foundMember = memberService.findById(savedMember.getId());
        System.out.println(foundMember.getId() + " " +  savedMember.getId());
        assertEquals(savedMember.getId(), foundMember.getId());
    }

    @Test
    void findAll() {
        MemberDTO memberDTO1 = generateTestObject();
        MemberDTO memberDTO2 = new MemberDTO(null, "Tugan", "Tekirdag", "tugan123@example.com", "12345","A1B2C3D4");

        MemberDTO saved1 = memberService.saveMember(memberDTO1);
        MemberDTO saved2 = memberService.saveMember(memberDTO2);

        List<MemberDTO> foundMembers = memberService.findAll();
        System.out.println(foundMembers);
        assertEquals(2, foundMembers.size());
        assertEquals(saved1.getId(), foundMembers.get(0).getId());
        assertEquals(saved2.getId(), foundMembers.get(1).getId());
    }


    @Test
    void findByName() {
        MemberDTO memberDTO = generateTestObject();
        MemberDTO savedMember = memberService.saveMember(memberDTO);
        MemberDTO foundMember = memberService.findById(savedMember.getId());
        assertEquals(savedMember.getId(), foundMember.getId());
    }

    @Test
    void deleteMemberById() {
        // Arrange
        MemberDTO memberDTO = generateTestObject();
        MemberDTO savedMember = memberService.saveMember(memberDTO);

        // Act
        memberService.deleteMemberById(savedMember.getId());
        MemberDTO foundMember = memberService.findById(savedMember.getId());

        // Assert
        assertNull(foundMember, "Member should be null after deletion!");
    }

    @AfterEach
    void deleteAll() {
        memberService.deleteAll();
    }
}