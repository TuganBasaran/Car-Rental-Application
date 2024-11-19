package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.Member;

import java.util.List;

public interface MemberService {
    Member registerMember(Member member); // Register a new member
    Member updateMember(Long id, Member member); // Update member details
    Member getMemberById(Long id); // Fetch a member by ID
    List<Member> getAllMembers(); // List all members
    void deleteMember(Long id); // Delete a member by ID
}
