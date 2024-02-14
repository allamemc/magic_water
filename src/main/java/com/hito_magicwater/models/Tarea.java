package com.hito_magicwater.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtarea;
    @ManyToOne
    @JoinColumn(name="NIF")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="idproyecto")
    private Proyecto proyecto;
    private String titulo;
    private String descripcion;
    private Date inicioprevisto;
    private Date finprevisto;
    private Date inicioreal;
    private Date finreal;
    private String estado;
}