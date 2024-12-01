package cs_393_TZS.car_rental_application.controller;

import cs_393_TZS.car_rental_application.DTO.MemberDTO;
import cs_393_TZS.car_rental_application.model.Member;
import cs_393_TZS.car_rental_application.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Convert Member to MemberDTO
    private MemberDTO toMemberDTO(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getName(),
                member.getAddress(),
                member.getEmail(),
                member.getPhone(),
                member.getDrivingLicenseNumber()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        try {
            Member member = memberService.findMemberById(id);
            return ResponseEntity.ok(toMemberDTO(member));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 Not Found
        }
    }


    // Add a new member
    @PostMapping
    public ResponseEntity<MemberDTO> addMember(@RequestBody Member member) {
        Member savedMember = memberService.saveMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(toMemberDTO(savedMember));
    }



    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<Member> members = memberService.findAllMembers();
        List<MemberDTO> memberDTOs = members.stream()
                .map(this::toMemberDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(memberDTOs);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMemberById(@PathVariable Long id) {
        try {
            memberService.deleteMemberById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping
    public ResponseEntity<?> deleteAllMembers() {
        memberService.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
