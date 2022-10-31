package com.portfolio.jea.Controller;

import com.portfolio.jea.Dto.dtoHysSkills;
import com.portfolio.jea.Entity.HysSkills;
import com.portfolio.jea.Security.Controller.Mensaje;
import com.portfolio.jea.Service.SHysSkills;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://frontendjea.web.app/", "http://localhost:4200"})
@RequestMapping("/skill")
public class CHysSkills {

    @Autowired
    SHysSkills sHysSkills;

    @GetMapping("/lista")
    public ResponseEntity<List<HysSkills>> list() {
        List<HysSkills> list = sHysSkills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHysSkills dtohys) {
        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (sHysSkills.existsByNombre(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe."), HttpStatus.BAD_REQUEST);
        }

        HysSkills hysSkills = new HysSkills(dtohys.getNombre(), dtohys.getPorcentaje());
        sHysSkills.save(hysSkills);

        return new ResponseEntity(new Mensaje("Skill agregada."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHysSkills dtohys) {
        // Verifica si existe el ID
        if (!sHysSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe."), HttpStatus.BAD_REQUEST);
        }

        // Compara nombre de experiencias
        if (sHysSkills.existsByNombre(dtohys.getNombre()) && sHysSkills.getByNombre(dtohys.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe."), HttpStatus.BAD_REQUEST);
        }

        // No puede estar vacio
        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }

        HysSkills hysSkills = sHysSkills.getOne(id).get();
        hysSkills.setNombre(dtohys.getNombre());
        hysSkills.setPorcentaje(dtohys.getPorcentaje());

        sHysSkills.save(hysSkills);
        return new ResponseEntity(new Mensaje("Skill actualizada."), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        // Verifica si existe el ID
        if (!sHysSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe."), HttpStatus.BAD_REQUEST);
        }

        sHysSkills.delete(id);

        return new ResponseEntity(new Mensaje("Skill eliminada."), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HysSkills> getById(@PathVariable("id") int id) {
        if (!sHysSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe."), HttpStatus.NOT_FOUND);
        }
        HysSkills hysSkills = sHysSkills.getOne(id).get();
        return new ResponseEntity(hysSkills, HttpStatus.OK);
    }

}
