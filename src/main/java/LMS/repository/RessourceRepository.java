package LMS.repository;


import LMS.model.Ressource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RessourceRepository extends JpaRepository<Ressource, Long>{
    Page<Ressource> findByNomContains(String kw, Pageable pageable);
}

