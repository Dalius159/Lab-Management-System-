package LMS.service;

import java.util.List;

import LMS.repository.MembreRepository;
import LMS.repository.ProjetRepository;
import LMS.repository.PublicationRepository;
import LMS.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableauService {

    @Autowired
    private MembreRepository membreRepository ;

    @Autowired
    private PublicationRepository publicationRepository ;

    @Autowired
    private ProjetRepository projetRepository ;

    @Autowired
    private RoleRepository roleRepository;

    public Long countM(){
        return membreRepository.count() ;
    }

    public Long countP(){
        return projetRepository.count() ;
    }
    public Long countPub(){
        return publicationRepository.count() ;
    }
    public List<Object[]> countUsersByRole() {
        return roleRepository.countUsersByRole();
    }
}

