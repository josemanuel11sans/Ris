package com.example.Ris.Paciente.controller;

import com.example.Ris.Paciente.model.PacienteDTO;
import com.example.Ris.Utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllPacientes() {
        return pacienteService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> savePaciente(@Validated(PacienteDTO.Register.class) @RequestBody PacienteDTO dto) {
        return pacienteService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updatePaciente(@Validated(PacienteDTO.Modify.class) @RequestBody PacienteDTO dto) {
        return pacienteService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changeStatus(@Validated(PacienteDTO.ChangeStatus.class) @RequestBody PacienteDTO dto) {
        return pacienteService.changeStatus(dto);
    }
}
