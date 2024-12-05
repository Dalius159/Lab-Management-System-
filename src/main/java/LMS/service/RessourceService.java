package LMS.service;

import java.util.List;

import LMS.model.Ressource;
import LMS.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RessourceService {
    @Autowired
    private RessourceRepository ressourceRepository ;

    public List<Ressource> findAllRes(){
        List<Ressource> res = ressourceRepository.findAll() ;
        return res ;
    }

    public void save(Ressource res){
        ressourceRepository.save(res) ;
    }

    public void deleteRes(Long id ){
        ressourceRepository.deleteById(id);
    }

    public Ressource findById(Long id) {
        return ressourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }
}

