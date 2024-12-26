
package com.nhat.spring.Repository;


import com.nhat.spring.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PublicationRepository  extends JpaRepository< Publication , Long > {
    long count() ;
    Page<Publication> findByTitleContains(String kw, Pageable pageable);
}
