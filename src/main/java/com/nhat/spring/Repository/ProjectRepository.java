
package com.nhat.spring.Repository;

import com.nhat.spring.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository< Project , Long> {
    long count() ;
    Page<Project> findByTitleContains(String kw, Pageable pageable);
}
