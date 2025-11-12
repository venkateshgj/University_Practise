package com.springboot.University.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Professor extends JpaRepository<Professor ,Long> {
}
