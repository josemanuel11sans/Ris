package com.example.Ris.Medico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

//    @GetMapping("/all")
//    public ResponseEntity<Message> getAllMedicos() {
//        return medicoService.findAll();
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<Message> saveMedico(@Validated(PacienteDTO.Register.class) @RequestBody PacienteDTO dto) {
//        return medicoService.save(dto);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Message> updateMedico(@Validated(PacienteDTO.Modify.class) @RequestBody PacienteDTO dto) {
//        return medicoService.update(dto);
//    }
//
//    @PutMapping("/change-status")
//    public ResponseEntity<Message> changeStatus(@Validated(PacienteDTO.ChangeStatus.class) @RequestBody PacienteDTO dto) {
//        return medicoService.changeStatus(dto);
//    }
}
