
package com.nhat.spring.controller;

import com.nhat.spring.Repository.ResourceRepository;
import com.nhat.spring.model.Resource;
import com.nhat.spring.service.ResourceService;
import jakarta.validation.Valid;
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

public class ResourceController {
    @Autowired
    private ResourceRepository resourceRepository ;
    private final ResourceService resourceService ;
    
    @GetMapping("resource")
    public String listRes( Model model , @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw) {
        
        Page<Resource> res = resourceRepository.findByNameContains(kw, PageRequest.of(page,size));
        model.addAttribute("res",res.getContent());
        model.addAttribute("pages",new int[res.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        //List<Resource> resources = resourceService.findAllProject();
        //model.addAttribute( "res" , resources);
        return "resource" ;
    }
    
    @GetMapping("adminRes/addResource")
    public String addRes(Model model){
        Resource res = new Resource() ;
        model.addAttribute("res" , res) ;
        return "addResource" ;
    }
    
    @PostMapping("/adminRes/save")
    public String saveRes(@Valid @ModelAttribute("res") Resource resource,
                               BindingResult result,
                               Model model){
       
        resourceService.save(resource);
        return "redirect:/resource" ;
    }
    
    
    
    @GetMapping("/adminRes/deleteRes/{id}")
    public String deleteRes(@PathVariable(value ="id") Long id ){
        resourceService.deleteRes(id) ;
        return "redirect:/resource" ;
    }
    
    @GetMapping("/adminRes/editRes/{id}")
    public String showEditRes(@PathVariable Long id, Model model) {
        Resource resource = resourceService.findById(id) ;
        model.addAttribute("res", resource);
        return "editResource";
    }

    @PostMapping("/adminRes/update")
    public String updateRes(@Valid @ModelAttribute("res") Resource resource , BindingResult result, Model model) {
        resourceService.save(resource);
        return "redirect:/resource";
    }
    
}
