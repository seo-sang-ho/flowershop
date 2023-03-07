package university.flowershop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import university.flowershop.domain.Notice;
import university.flowershop.service.NoticeService;
import university.flowershop.session.SessionConst;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/noticeForm")
    public String noticeForm(Model model, HttpSession httpSession) {
        NoticeForm noticeForm = new NoticeForm();
        String loginId = (String) httpSession.getAttribute(SessionConst.LOGIN_MEMBER);
        noticeForm.setLoginId(loginId);
        model.addAttribute("noticeForm", noticeForm);
        return "notice/addNotice";
    }

    @PostMapping("/add/notice")
    public String addNotice(@ModelAttribute("noticeForm") @Valid NoticeForm noticeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "notices/addNotice";
        }

        Notice notice = new Notice();
        notice.setTitle(noticeForm.getTitle());
        notice.setContent(noticeForm.getContent());
        notice.setCreatedAt(LocalDateTime.now());

        noticeService.saveNotice(notice);

        log.info("공지사항 작성 메서드 실행");

        return "redirect:/notice/noticeDetail";
    }
}
