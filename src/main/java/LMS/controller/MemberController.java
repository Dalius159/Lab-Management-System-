package LMS.controller;

import LMS.model.Member;
import LMS.repository.MemberRepository;
import LMS.service.MemberService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberRepository memberRepository ;
    private final MemberService memberService;


    @GetMapping({"/" , "/login" })
    public String loginForm(Model model) {
        Member user = new Member();
        model.addAttribute("member", user);
        return "login";
    }

    @GetMapping("TEST" )
    public String l(Model model) {
        Member user = new Member();
        model.addAttribute("member", user);
        return "TEST";
    }


    @GetMapping("adminM/AddMember")
    public String add(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "AddMember";
    }

    @PostMapping("/save")
    public String save(@RequestParam("rol") List<String> selectedRoles  ,
                        @Valid @ModelAttribute("member") Member user,
                        BindingResult result,
                        Model model) {

        Member existing = memberService.findByEmail(user.getEmail());

        if ( existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
            return "redirect:/adminM/AddMember?error";
        }
        if (result.hasErrors()) {
            model.addAttribute("model", user);
            return "AddMember";
        }
        memberService.saveUser(selectedRoles , user);
        return "redirect:/member";
    }


    @PostMapping("/register/save")
    public String saveUsers(@RequestParam("rol") List<String> selectedRoles  ,
                            @Valid @ModelAttribute("member") Member user,
                            BindingResult result,
                            Model model) {
        Member existing = memberService.findByEmail(user.getEmail());

        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
            return "redirect:/login?error2";
        }

        if (result.hasErrors()) {
            model.addAttribute("model", user);
            return "login";
        }

        memberService.saveUser(selectedRoles , user);
        return "redirect:/login?success";

    }


    @GetMapping("member")
    public String Members(Model model ,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "4") int size,
                          @RequestParam(name = "keyword",defaultValue = "") String kw) {

        Page<Member> member = memberRepository.findByFirstNameContains(kw, PageRequest.of(page,size));
        model.addAttribute("members",member.getContent());
        model.addAttribute("pages",new int[member.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        //List<Member> members = memberService.findAllUsers();

        //model.addAttribute("members", members);
        return "member";
    }

    @GetMapping("/adminM/editMember/{id}")
    public String editMember(@PathVariable Long id, Model model) {
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
        return "editMember";
    }

    @PostMapping("/adminM/update")
    public String updateMember(@RequestParam("roles") List<String> selectedRoles  ,
                               @RequestParam("pass") String pass  ,
                               @Valid @ModelAttribute("member") Member memberDto ,
                               BindingResult result, Model model) {

        memberService.updateUser(memberDto ,selectedRoles , pass );

        return "redirect:/member";
    }

    @GetMapping("/adminM/delete/{id}")
    public String deleteMember(@PathVariable(value = "id") Long id) {
        memberService.deleteUserById(id);
        return "redirect:/member";
    }
}

