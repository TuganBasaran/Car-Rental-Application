package cs_393_TZS.car_rental_application.repository;

import cs_393_TZS.car_rental_application.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
