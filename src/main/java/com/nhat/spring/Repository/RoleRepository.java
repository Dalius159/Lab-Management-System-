
package com.nhat.spring.Repository;


import com.nhat.spring.model.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    
    @Query("SELECT r.name, COUNT(m) FROM Role r JOIN r.members m GROUP BY r.name")
    List<Object[]> countUsersByRole();
}
