package university.flowershop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import university.flowershop.domain.Member;
import university.flowershop.repository.MemberRepository;
import university.flowershop.service.MemberService;
import university.flowershop.session.SessionConst;
import university.flowershop.session.SessionManager;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;


    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {


        //세션에 데이터가 없으면 home 으로 이동
        if (loginMember == null) {
            return "index";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginIndex";
    }

    @GetMapping("/loginIndex")
    public String loginIndex(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        if (loginMember == null) {
            return "redirect:/";
        }

        model.addAttribute("member", loginMember);
        return "loginIndex";
    }

    @GetMapping("flowerDictionary")
    public String flowerDictionary() {
        return "flowerDictionary";
    }

    @GetMapping("/brand")
    public String brand() {
        return "brand";
    }
}
