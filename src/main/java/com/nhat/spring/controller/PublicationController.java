
package com.nhat.spring.controller;


import com.nhat.spring.Repository.PublicationRepository;
import com.nhat.spring.model.Project;
import com.nhat.spring.model.Publication;
import com.nhat.spring.service.ProjectService;
import com.nhat.spring.service.PublicationService;
import jakarta.validation.Valid;
import java.util.Date;
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


public class PublicationController {
    @Autowired
    private PublicationRepository publicationRepository ;
    private final PublicationService publicationService;
    private final ProjectService projectService ;
    
    @GetMapping("publication")
    public String listPub( Model model , @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw) {
        
        Page<Publication> pub = publicationRepository.findByTitleContains(kw, PageRequest.of(page,size));
        model.addAttribute("pubs",pub.getContent());
        model.addAttribute("pages",new int[pub.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        //List<Publication> pubs = publicationService.findAllProject();
        //model.addAttribute( "pubs" , pubs);
        return "publication" ;
    }
    
    @GetMapping("/addPublication/{id}")
    public String addPub(Model model , @PathVariable(value ="id") Long id  ){
        Project project = projectService.findById(id) ;
        Publication pub = new Publication() ;
        pub.setProject(project);
        model.addAttribute("pub" , pub) ;
        return "addPublication" ;
    }
    
    @PostMapping("/addPublication/save")
    public String savePub(@Valid @ModelAttribute("pub") Publication pub,
                               BindingResult result,
                               Model model){
        
        
        pub.setDate_pub(new Date());
        pub.setAuthor(publicationService.UserName());
        pub.setProject(pub.getProject());
        publicationService.save(pub);
        return "redirect:/publication" ;
    }
    
   
    
    @GetMapping("/adminPub/deletePub/{id}")
    public String deletePub(@PathVariable(value ="id") Long id ){
        publicationService.deletePub(id) ;
        return "redirect:/publication" ;
    }
    
    @GetMapping("/adminPub/editPub/{id}")
    public String showEditPub(@PathVariable Long id, Model model) {
        Publication publication = publicationService.findById(id) ;
        model.addAttribute("pub", publication);
        return "editPublication";
    }

    @PostMapping("/adminPub/update")
    public String updatePub(@Valid @ModelAttribute("pub") Publication pub , BindingResult result, Model model) {
        Long id= pub.getId() ;
        Publication publication = publicationService.findById(id) ;
        
        pub.setAuthor(publication.getAuthor());
        pub.setProject(publication.getProject()); 
        pub.setDate_pub(publication.getDate_pub());
        
        publicationService.save(pub);
        return "redirect:/publication";
    }
    
    
}
