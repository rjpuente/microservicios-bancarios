package com.rdevelop.tech_test.repository;

import com.rdevelop.tech_test.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
