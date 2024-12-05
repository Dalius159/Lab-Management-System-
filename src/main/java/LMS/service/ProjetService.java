package LMS.service;

import java.util.List;

import LMS.model.Projet;
import LMS.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepository projetRepository ;

    public List<Projet> findAllProjet(){
        List<Projet> projets = projetRepository.findAll() ;
        return projets ;
    }

    public void save(Projet projet){
        System.out.println(projet.getDate_debut());
        projetRepository.save(projet) ;
    }

    public void deleteProjet(Long id ){
        projetRepository.deleteById(id);
    }

    public Projet findById(Long id) {
        return projetRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }
}

