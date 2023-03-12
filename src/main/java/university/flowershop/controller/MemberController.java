package university.flowershop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import university.flowershop.domain.Address;
import university.flowershop.domain.Member;
import university.flowershop.exception.MemberNotFoundException;
import university.flowershop.service.MemberService;
import university.flowershop.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new") //회원가입 폼으로 간다.
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("alertMsg", "모든 정보를 입력해주세요");
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setPhone(form.getPhone());
        member.setEmail(form.getEmail());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/create-success";
    }

    @GetMapping("/create-success")
    public String createSuccess(Model model) {
        String loginId = "loginId";
        model.addAttribute("loginId", loginId);
        return "members/create-success";
    }


    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/findId")
    public String findIdByPasswordForm() {
        return "members/findLoginIdForm";
    }

    @PostMapping("/members/findId")
    public String findIdByNamePhone(@RequestParam("name") String name, @RequestParam("phone") String phone, Model model) {
        try {
            String loginId = memberService.findLoginIdByPassword(name, phone);
            model.addAttribute("loginId", loginId);
            return "members/findLoginId";
        } catch (MemberNotFoundException e) {
            model.addAttribute("error", "아이디가 존재하지 않습니다");
            return "members/findLoginIdForm";
        }
    }



    @GetMapping("/members/findPassword")
    public String findPasswordByIdForm() {
        return "members/findPasswordForm";
    }

    @PostMapping("/members/findPassword")
    public String findPasswordById(@RequestParam("name") String name, @RequestParam("loginId") String loginId, Model model) {
        try {
            String password = memberService.findPasswordByNameLoginId(name, loginId);
            model.addAttribute("password", password);
            return "members/findPassword";
        } catch (MemberNotFoundException e) {
            model.addAttribute("error", "입력하신 아이디가 존재하지 않습니다");
            return "members/findPasswordForm";
        }
    }

    @GetMapping("/members/myPage")
    public String myPageForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Member foundMember = memberService.getMemberByName(member.getId());
        model.addAttribute("member", foundMember);
        return "members/myPage";
    }

    @GetMapping("/members/myPage/edit")
    public String editForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Member foundMember = memberService.getMemberByName(member.getId());
        model.addAttribute("member", foundMember);
        return "members/editForm";
    }

    @PostMapping("/members/myPage/edit")
    public String updateMember(@ModelAttribute("memberForm") MemberForm memberForm, HttpSession session, Model model) {
        // HttpSession에서 로그인한 멤버 정보를 가져옵니다.
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);


        // 수정할 회원 정보를 MemberForm에서 가져와서 Member 객체로 변환합니다.

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setId(loginMember.getId());
        member.setName(memberForm.getName());
        member.setPhone(memberForm.getPhone());
        member.setLoginId(memberForm.getLoginId());
        member.setPassword(memberForm.getPassword());
        member.setEmail(memberForm.getEmail());
        member.setAddress(address);

        // MemberService를 사용하여 회원 정보를 업데이트합니다.
        memberService.updateMember(member);

        // 마이페이지로 리다이렉트합니다.
        return "redirect:/members/myPage";
    }




}
