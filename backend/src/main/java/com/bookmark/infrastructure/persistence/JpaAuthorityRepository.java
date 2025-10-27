package com.bookmark.infrastructure.persistence;

import com.bookmark.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAuthorityRepository extends JpaRepository<Authority, Long> {}
