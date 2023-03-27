package com.test.repositories;

import com.test.entities.Equation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquationRepository extends JpaRepository<Equation, Integer> {
}
