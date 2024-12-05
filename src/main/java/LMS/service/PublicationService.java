package LMS.service;

import java.util.Date;
import java.util.List;

import LMS.model.Publication;
import LMS.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository ;

    public List<Publication> findAllPub(){
        List<Publication> pubs = publicationRepository.findAll() ;
        return pubs ;
    }

    public void save(Publication pub){

        publicationRepository.save(pub) ;
    }

    public void deletePub(Long id ){
        publicationRepository.deleteById(id);
    }

    public Publication findById(Long id) {
        return publicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public Date date(Long id){
        Publication pub = publicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found")) ;
        return pub.getDate_pub() ;
    }
    public String UserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null && authentication.isAuthenticated()) {

            String nomUtilisateur = authentication.getName();
            return nomUtilisateur;
        }

        return null;
    }
}
