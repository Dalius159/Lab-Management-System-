package LMS.repository;

import LMS.model.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
    long count() ;
    Page<Projet> findByTitreContains(String kw, Pageable pageable);
}