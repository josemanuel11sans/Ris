package com.example.Ris.Paciente.controller;

import com.example.Ris.Paciente.model.Paciente;
import com.example.Ris.Paciente.model.PacienteDTO;
import com.example.Ris.Paciente.model.PacienteRepository;
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
public class PacienteService {
    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        logger.info("La búsqueda de pacientes ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(pacientes, "Listado de pacientes", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(PacienteDTO dto) {
        if (dto.getNombre().length() > 50) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getApellidos().length() > 100) {
            return new ResponseEntity<>(new Message("Los apellidos exceden el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Paciente paciente = new Paciente(dto.getNombre(), dto.getApellidos(), dto.getTelefono(), true);
        paciente = pacienteRepository.saveAndFlush(paciente);
        if (paciente == null) {
            return new ResponseEntity<>(new Message("El paciente no se registró", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("El registro del paciente se realizó correctamente");
        return new ResponseEntity<>(new Message(paciente, "El paciente se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(PacienteDTO dto) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(dto.getId());
        if (!pacienteOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El paciente no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
        if (dto.getNombre().length() > 50) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getApellidos().length() > 100) {
            return new ResponseEntity<>(new Message("Los apellidos exceden el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Paciente paciente = pacienteOptional.get();
        paciente.setNombre(dto.getNombre());
        paciente.setApellidos(dto.getApellidos());
        paciente.setTelefono(dto.getTelefono());
        paciente = pacienteRepository.saveAndFlush(paciente);
        if (paciente == null) {
            return new ResponseEntity<>(new Message("El paciente no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización del paciente se realizó correctamente");
        return new ResponseEntity<>(new Message(paciente, "El paciente se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(PacienteDTO dto) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(dto.getId());
        if (!pacienteOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El paciente no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
        Paciente paciente = pacienteOptional.get();
        paciente.setStatus(!paciente.isStatus());
        paciente = pacienteRepository.saveAndFlush(paciente);
        if (paciente == null) {
            return new ResponseEntity<>(new Message("El estado del paciente no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("El cambio de estado del paciente se realizó correctamente");
        return new ResponseEntity<>(new Message(paciente, "El estado del paciente se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
