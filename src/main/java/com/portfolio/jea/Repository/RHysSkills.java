package com.portfolio.jea.Repository;

import com.portfolio.jea.Entity.HysSkills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RHysSkills extends JpaRepository<HysSkills, Integer> {
    Optional<HysSkills> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
