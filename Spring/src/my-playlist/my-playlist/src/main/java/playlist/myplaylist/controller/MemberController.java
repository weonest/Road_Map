package playlist.myplaylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import playlist.myplaylist.domain.Member;
import playlist.myplaylist.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPhone(form.getPhone());

        memberService.join(member);

        return "redirect:/";
    }
}
