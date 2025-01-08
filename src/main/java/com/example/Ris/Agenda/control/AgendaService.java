package com.example.Ris.Agenda.control;

import com.example.Ris.Agenda.model.Agenda;
import com.example.Ris.Agenda.model.AgendaDTO;
import com.example.Ris.Agenda.model.AgendaRepository;
import com.example.Ris.Utils.Message;
import com.example.Ris.Utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AgendaService {
    private static final Logger logger = LoggerFactory.getLogger(AgendaService.class);

    private final AgendaRepository agendaRepository;

    @Autowired
    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Agenda> games = agendaRepository.findAll();
        logger.info("La búsqueda ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(games,"Listado de agendas", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(AgendaDTO dto) {
        if(dto.getDia().length() > 60) {
            return new ResponseEntity<>(new Message("El dia excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getHora().length() > 50) {
            return new ResponseEntity<>(new Message("La hora excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        Agenda agenda = new Agenda(dto.getDia(),dto.getHora(),dto.getUbicacion(), dto.getMotivo(), true);
        agenda = agendaRepository.saveAndFlush(agenda);
        if(agenda == null){
            return new ResponseEntity<>(new Message("La cita no se registró",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("La cita se ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(agenda,"La cita se registró correctamente",TypesResponse.SUCCESS),HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(AgendaDTO dto) {
        Optional<Agenda> agendaOptional = agendaRepository.findById(dto.getId());
        if(!agendaOptional.isPresent()){
            return new ResponseEntity<>(new Message("La cita no existe",TypesResponse.ERROR),HttpStatus.NOT_FOUND);
        }
        if(dto.getDia().length() > 60) {
            return new ResponseEntity<>(new Message("El dia excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getHora().length() > 50) {
            return new ResponseEntity<>(new Message("La hora excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }

        Agenda agenda = agendaOptional.get();
        agenda.setDia(dto.getDia());
        agenda.setHora(dto.getHora());
        agenda.setUbicacion(dto.getUbicacion());
        agenda.setMotivo(dto.getMotivo());
        agenda = agendaRepository.saveAndFlush(agenda);
        if(agenda == null){
            return new ResponseEntity<>(new Message("La cita no se actualizó",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(agenda,"La cita se actualizó correctamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(AgendaDTO dto) {
        Optional<Agenda> agendaOptional = agendaRepository.findById(dto.getId());
        if(!agendaOptional.isPresent()){
            return new ResponseEntity<>(new Message("La cita no existe",TypesResponse.ERROR),HttpStatus.NOT_FOUND);
        }
        Agenda agenda = agendaOptional.get();
        agenda.setStatus(!agenda.isStatus());
        agenda = agendaRepository.saveAndFlush(agenda);
        if(agenda == null){
            return new ResponseEntity<>(new Message("El estado de la cita no se actualizó",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(agenda,"El estado de la citaw se actualizó correctamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }
}