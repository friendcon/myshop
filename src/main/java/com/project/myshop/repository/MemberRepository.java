package com.project.myshop.repository;

import com.project.myshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public boolean existsByUsername(String username);
}
