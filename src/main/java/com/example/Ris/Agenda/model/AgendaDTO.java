package com.example.Ris.Agenda.model;

import jakarta.validation.constraints.NotBlank;

public class AgendaDTO {
    @NotBlank(groups = {Register.class,Modify.class})
    private String dia;
    @NotBlank(groups = {Register.class,Modify.class})
    private String hora;
    @NotBlank(groups = {Register.class,Modify.class})
    private String ubicacion;
    @NotBlank(groups = {Register.class,Modify.class})
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

    public interface Register{}
    public interface Modify{}
    public interface ChangeStatus{}
}