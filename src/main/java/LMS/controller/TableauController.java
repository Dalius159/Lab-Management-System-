package LMS.controller;

import java.util.List;

import LMS.model.Member;
import LMS.service.MemberService;
import LMS.service.PublicationService;
import LMS.service.TableauService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TableauController {
    private final TableauService tableauService ;
    private final PublicationService publicationService ;
    private final MemberService memberService ;

    @GetMapping("Home")
    public String home(Model model){

        String username = publicationService.UserName()  ;
        Member member = memberService.findByEmail(username) ;
        model.addAttribute("member" , member ) ;
        model.addAttribute("m" , tableauService.countM()) ;
        model.addAttribute("p" , tableauService.countP()) ;
        model.addAttribute("pub" , tableauService.countPub()) ;
        List<Object[]> roleCounts = tableauService.countUsersByRole();
        model.addAttribute("roleCounts", roleCounts);

        return "Home" ;
    }
}

