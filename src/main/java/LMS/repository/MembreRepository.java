package LMS.repository;

import LMS.model.Membre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {
    Membre findByEmail(String email);
    long count();
    Page<Membre> findByFirstNameContains(String kw, Pageable pageable);
}
