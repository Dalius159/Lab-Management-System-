
package com.nhat.spring.Repository;


import com.nhat.spring.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long>{
    Page<Resource> findByNameContains(String kw, Pageable pageable);
}
