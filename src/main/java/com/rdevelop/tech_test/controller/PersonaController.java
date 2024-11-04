package com.rdevelop.tech_test.controller;

import com.rdevelop.tech_test.entity.Persona;
import com.rdevelop.tech_test.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public List<Persona> obtenerTodasLasPersonas() {
        return personaService.findAllPersona();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        Optional<Persona> persona = personaService.findByPersonaId(id);
        return persona.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Persona crearPersona(@RequestBody Persona persona) {
        return personaService.createPersona(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) {
        if (personaService.findByPersonaId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        persona.setId(id);
        return ResponseEntity.ok(personaService.updatePersona(persona));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        if (!personaService.findByPersonaId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }

}
