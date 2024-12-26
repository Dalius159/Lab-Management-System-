package com.nhat.spring.controller;


import com.nhat.spring.Repository.ProjectRepository;
import com.nhat.spring.model.Project;
import com.nhat.spring.model.Resource;
import com.nhat.spring.service.ProjectService;
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
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository ;
    private final ProjectService projectService;
    
    @GetMapping("project")
    public String listProject( Model model , @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw) {
        
        Page<Project> project = projectRepository.findByTitleContains(kw, PageRequest.of(page,size));
        model.addAttribute("projects",project.getContent());
        model.addAttribute("pages",new int[project.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        //List<Project> projects = projectService.findAllProject();
        //model.addAttribute( "projects" , projects);
        return "project" ;
    }

    @GetMapping("/adminP/addProject")
    public String addProject(Model model){
        Project project = new Project() ;
        model.addAttribute("project" , project) ;
        return "addProject" ;
    }

    @PostMapping("/adminP/save")
    public String saveProject(@Valid @ModelAttribute("project") Project project,
                               BindingResult result,
                               Model model){

        projectService.save(project);
        return "redirect:/project" ;
    }
    
    
    @GetMapping("/project/descProject/{description}")
    public String desc(@PathVariable(value = "description") String description , Model model){
  
        model.addAttribute("des" , description ) ;
        return "description" ;
    }
    
    @GetMapping("description")
    public String description(){
        return "description" ;
    }
    
    @GetMapping("/adminP/deleteProject/{id}")
    public String deleteProj(@PathVariable(value ="id") Long id ){
        projectService.deleteProject(id) ;
        return "redirect:/project" ;
    }
    
    @GetMapping("/adminP/editProject/{id}")
    public String showEditUsersForm(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id) ;
        model.addAttribute("project", project);
        return "editProject";
    }

    @PostMapping("/adminP/update")
    public String updateUser(@Valid @ModelAttribute("project") Project project , BindingResult result, Model model) {
        projectService.save(project);
        return "redirect:/project";
    }
    
}
