package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.MemberDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationRequestDTO;
import cs_393_TZS.car_rental_application.model.*;
import cs_393_TZS.car_rental_application.repository.CarRepository;
import cs_393_TZS.car_rental_application.repository.LocationRepository;
import cs_393_TZS.car_rental_application.repository.MemberRepository;
import cs_393_TZS.car_rental_application.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.MetaMessage;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberDTO toDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setName(member.getName());
        memberDTO.setAddress(member.getAddress());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPhone(member.getPhone());
        memberDTO.setDrivingLicenseNumber(member.getDrivingLicenseNumber());
        return memberDTO;
    }

    public Member toEntity(MemberDTO memberDTO) {
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setName(memberDTO.getName());
        member.setPhone(memberDTO.getPhone());
        member.setEmail(memberDTO.getEmail());
        member.setAddress(memberDTO.getAddress());
        member.setDrivingLicenseNumber(memberDTO.getDrivingLicenseNumber());
        return member;
    }

    public MemberDTO saveMember(MemberDTO memberDTO) {
        Member member = toEntity(memberDTO);
        Member savedMember = memberRepository.save(member);
        return toDTO(savedMember);
    }

    public List<MemberDTO> findAll() {
        List<Member> foundMembers = memberRepository.findAll();
        return foundMembers.stream().map(this::toDTO).toList();
    }

    public MemberDTO findById(Long id) {
        Member foundMember = memberRepository.findMemberById(id);
        if (foundMember != null) return toDTO(foundMember);
        else return null;
    }

    public List<MemberDTO> findByName(String name) {
        Optional<Member> foundMembers = memberRepository.findByName(name);
        return foundMembers.stream().map(this::toDTO).toList();
    }

    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    public void deleteAll() {
        memberRepository.deleteAll();
    }

}