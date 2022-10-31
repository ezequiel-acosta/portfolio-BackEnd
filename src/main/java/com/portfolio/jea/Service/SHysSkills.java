package com.portfolio.jea.Service;

import com.portfolio.jea.Entity.HysSkills;
import com.portfolio.jea.Repository.RHysSkills;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SHysSkills {
    @Autowired
    RHysSkills rHysSkills;
    
    public List<HysSkills> list() {
        return rHysSkills.findAll();
    }
    
    public Optional<HysSkills> getOne(int id) {
        return rHysSkills.findById(id);
    }
    
    public Optional<HysSkills> getByNombre(String nombre) {
        return rHysSkills.findByNombre(nombre);
    }
    
    public void save(HysSkills skill) {
        rHysSkills.save(skill);
    }
    
    public void delete(int id) {
        rHysSkills.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return rHysSkills.existsById(id);
    }
    
    public boolean existsByNombre(String nombre) {
        return rHysSkills.existsByNombre(nombre);
    }
    
}
