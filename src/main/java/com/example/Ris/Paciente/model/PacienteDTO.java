package com.example.Ris.Paciente.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PacienteDTO {

    @NotNull(groups = {Modify.class, ChangeStatus.class}, message = "Es necesario el id")
    private Long id;

    @NotBlank(groups = {Register.class, Modify.class}, message = "El nombre no puede ser nulo")
    private String nombre;

    @NotBlank(groups = {Register.class, Modify.class}, message = "Los apellidos no pueden ser nulos")
    private String apellidos;

    @NotBlank(groups = {Register.class, Modify.class}, message = "El teléfono no puede ser nulo")
    private String telefono;

    public PacienteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public interface Register {}
    public interface Modify {}
    public interface ChangeStatus {}
}
