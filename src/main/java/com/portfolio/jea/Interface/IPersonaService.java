package com.portfolio.jea.Interface;

import com.portfolio.jea.Entity.Persona;
import java.util.List;

public interface IPersonaService {
    // Traer una lista de personas
    public List<Persona> getPersona();
    
    // Guardar un objeto de tipo Persona
    public void savePersona(Persona persona);
    
    // Eliminar un objeto buscado por Id
    public void deletePersona(Long id);
    
    // Buscar un objeto de tipo Persona buscado por Id
    public Persona findPersona(Long id);
}
