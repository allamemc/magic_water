package com.hito_magicwater.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Proyecto {
    @Id
    private int idproyecto;
    private String nombre;
    private String descripcion;
    private String zona;
    private String fecha;


}