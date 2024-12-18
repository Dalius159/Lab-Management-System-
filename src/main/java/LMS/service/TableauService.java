package LMS.service;

import java.util.List;

import LMS.repository.MemberRepository;
import LMS.repository.ProjectRepository;
import LMS.repository.PublicationRepository;
import LMS.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableauService {

    @Autowired
    private MemberRepository memberRepository ;

    @Autowired
    private PublicationRepository publicationRepository ;

    @Autowired
    private ProjectRepository projectRepository ;

    @Autowired
    private RoleRepository roleRepository;

    public Long countM(){
        return memberRepository.count() ;
    }

    public Long countP(){
        return projectRepository.count() ;
    }
    public Long countPub(){
        return publicationRepository.count() ;
    }
    public List<Object[]> countUsersByRole() {
        return roleRepository.countUsersByRole();
    }
}

