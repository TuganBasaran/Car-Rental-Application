package cs_393_TZS.car_rental_application.controller;

import cs_393_TZS.car_rental_application.model.Member;
import cs_393_TZS.car_rental_application.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    
}
