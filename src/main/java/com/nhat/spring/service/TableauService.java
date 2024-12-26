
package com.nhat.spring.service;

import com.nhat.spring.Repository.MemberRepository;
import com.nhat.spring.Repository.ProjectRepository;
import com.nhat.spring.Repository.PublicationRepository;
import com.nhat.spring.Repository.RoleRepository;
import java.util.List;
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
