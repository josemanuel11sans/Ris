package com.example.Ris.Medico.model;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class MedicoDTO {
    @NotNull(groups = {Modify.class, ChangeStatus.class},message = "Es necesario el id")
    private Long id;
    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el nombre")
    private String nombre;
    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el apellido")
    private String apellido;
    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario la especialidad ")
    private String especialidade;

    public @NotNull(groups = {Modify.class, ChangeStatus.class}, message = "Es necesario el id") Long getId() {
        return id;
    }

    public void setId(@NotNull(groups = {Modify.class, ChangeStatus.class}, message = "Es necesario el id") Long id) {
        this.id = id;
    }

    public @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el nombre") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el nombre") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el apellido") String getApellido() {
        return apellido;
    }

    public void setApellido(@NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el apellido") String apellido) {
        this.apellido = apellido;
    }

    public @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario la especialidad ") String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(@NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario la especialidad ") String especialidade) {
        this.especialidade = especialidade;
    }

    public interface Register {}
    public interface Modify {}
    public interface ChangeStatus {}
}
