
package com.nhat.spring.service;

import com.nhat.spring.Repository.ProjectRepository;
import com.nhat.spring.model.Project;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class ProjectService {
    
    @Autowired 
    private ProjectRepository projectRepository ;
    
    public List<Project> findAllProject(){
        List<Project> projects = projectRepository.findAll() ;
        return projects ;
    }
    
    public void save(Project project){
        System.out.println(project.getStart_date());
        projectRepository.save(project) ;
    }
    
    public void deleteProject(Long id ){
        projectRepository.deleteById(id);
    }
    
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
