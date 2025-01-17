package com.example.Ris.Agenda.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AgendaDTO {

    @NotNull(groups = {Modify.class, ChangeStatus.class},message = "Es necesario el id")
    private Long id;
    @NotBlank(groups = {Register.class,Modify.class},message = "El dia no puede ser nulo")
    private String dia;
    @NotBlank(groups = {Register.class,Modify.class},message = "La hora no puede ser nula")
    private String hora;
    @NotBlank(groups = {Register.class,Modify.class},message = "La ubicacion no puede ser nula")
    private String ubicacion;
    @NotBlank(groups = {Register.class,Modify.class},message = "El motivo no puede ser nulo")
    private String motivo;

    public AgendaDTO() {
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public interface Register{}
    public interface Modify{}
    public interface ChangeStatus{}
}