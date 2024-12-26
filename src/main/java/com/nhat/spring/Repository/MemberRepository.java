
package com.nhat.spring.Repository;

import com.nhat.spring.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    long count();
    Page<Member> findByFirstNameContains(String kw, Pageable pageable);
}
