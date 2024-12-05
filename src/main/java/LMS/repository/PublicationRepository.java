package LMS.repository;

import LMS.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository  extends JpaRepository<Publication, Long > {
    long count() ;
    Page<Publication> findByTitreContains(String kw, Pageable pageable);
}

