package com.example.Ris.Medico.controller;

import com.example.Ris.Medico.model.MedicoDTO;
import com.example.Ris.Utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllMedicos() {
        return medicoService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveMedico(@Validated(MedicoDTO.Register.class) @RequestBody MedicoDTO dto) {
        return medicoService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateMedico(@Validated(MedicoDTO.Modify.class) @RequestBody MedicoDTO dto) {
        return medicoService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changeStatus(@Validated(MedicoDTO.ChangeStatus.class) @RequestBody MedicoDTO dto) {
        return medicoService.changeStatus(dto);
    }
}
