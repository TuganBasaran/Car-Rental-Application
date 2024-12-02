package cs_393_TZS.car_rental_application.repository;

import cs_393_TZS.car_rental_application.DTO.MemberDTO;
import cs_393_TZS.car_rental_application.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);

    Member findMemberById(Long id);
}
