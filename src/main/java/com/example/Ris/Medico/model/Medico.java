package com.example.Ris.Medico.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", columnDefinition = "VARCHAR(50)")
    private String nombre;
    @Column(name = "apellido", columnDefinition = "VARCHAR(50)")
    private String apellido;
    @Column(name = "especialidad", columnDefinition = "VARCHAR(50)")
    private String especialidade;
    @Column(name = "status",columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    public Medico( String nombre, String apellido, String especialidade, boolean status) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidade = especialidade;
        this.status = status;
    }
    public Medico() {

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
