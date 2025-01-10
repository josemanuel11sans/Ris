package com.example.Ris.Medico.controller;

import com.example.Ris.Medico.model.Medico;
import com.example.Ris.Medico.model.MedicoDTO;
import com.example.Ris.Medico.model.MedicoRepository;
import com.example.Ris.Paciente.model.Paciente;
import com.example.Ris.Paciente.model.PacienteDTO;
import com.example.Ris.Paciente.model.PacienteRepository;
import com.example.Ris.Utils.Message;
import com.example.Ris.Utils.TypesResponse;
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
public class MedicoService {
    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Medico> medicos = medicoRepository.findAll();
        return new ResponseEntity<>(new Message(medicos, "Listado de medicos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(MedicoDTO dto) {
        if (dto.getNombre().length() > 50) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getApellido().length() > 100) {
            return new ResponseEntity<>(new Message("Los apellidos exceden el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Medico medico = new Medico(dto.getNombre(), dto.getApellido(), dto.getEspecialidade(), true);
        medico = medicoRepository.saveAndFlush(medico);
        if (medico == null) {
            return new ResponseEntity<>(new Message("El medico no se registró", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(medico, "El medico se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(MedicoDTO dto) {
        Optional<Medico> medicoOptional = medicoRepository.findById(dto.getId());
        if (!medicoOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El medico no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
        if (dto.getNombre().length() > 50) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getApellido().length() > 50) {
            return new ResponseEntity<>(new Message("Los apellidos exceden el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Medico medico = medicoOptional.get();
        medico.setNombre(dto.getNombre());
        medico.setApellido(dto.getApellido());
        medico.setEspecialidade(dto.getEspecialidade());
        medico = medicoRepository.saveAndFlush(medico);
        if (medico == null) {
            return new ResponseEntity<>(new Message("El medico no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(medico, "El paciente se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(MedicoDTO dto) {
        Optional<Medico> medicoOptional = medicoRepository.findById(dto.getId());
        if (!medicoOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El medico no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
        Medico medico = medicoOptional.get();
        medico.setStatus(!medico.isStatus());
        medico = medicoRepository.saveAndFlush(medico);
        if (medico == null) {
            return new ResponseEntity<>(new Message("El estado del paciente no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(medico, "El estado del medico se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
