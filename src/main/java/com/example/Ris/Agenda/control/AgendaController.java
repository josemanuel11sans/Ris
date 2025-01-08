package com.example.Ris.Agenda.control;

import com.example.Ris.Agenda.model.AgendaDTO;
import com.example.Ris.Utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    @Autowired
    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllDocument() {
        return agendaService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveDocument(@Validated(AgendaDTO.Register.class) @RequestBody AgendaDTO dto) {
        return agendaService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateDocument(@Validated(AgendaDTO.Modify.class) @RequestBody AgendaDTO dto) {
        return agendaService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changeStatus(@Validated(AgendaDTO.ChangeStatus.class) @RequestBody AgendaDTO dto) {
        return agendaService.changeStatus(dto);
    }

}