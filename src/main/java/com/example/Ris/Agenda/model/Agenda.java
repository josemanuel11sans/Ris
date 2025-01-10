package com.example.Ris.Agenda.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia",columnDefinition = "VARCHAR(60)")
    private String dia;

    @Column(name = "hora",columnDefinition = "VARCHAR(50)")
    private String hora;

    @Column(name = "ubicacion",columnDefinition = "VARCHAR(100)")
    private String ubicacion;

    @Column(name = "motivo",columnDefinition = "VARCHAR(100)")
    private String motivo;

    @Column(name = "status",columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    public Agenda() {
    }

    public Agenda(String dia, String hora, String ubicacion, String motivo,boolean status) {
        this.dia = dia;
        this.hora = hora;
        this.ubicacion=ubicacion;
        this.motivo = motivo;
        this.status = status;
    }

    public Agenda(Long id, String dia, String hora, String ubicacion, String motivo,boolean status) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.ubicacion=ubicacion;
        this.motivo = motivo;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}