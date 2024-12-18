package LMS.service;

import java.util.List;

import LMS.model.Project;
import LMS.repository.ProjectRepository;
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
        System.out.println(project.getDate_debut());
        projectRepository.save(project) ;
    }

    public void deleteProject(Long id ){
        projectRepository.deleteById(id);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }
}

