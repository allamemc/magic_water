package com.hito_magicwater.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproyecto;
    private String nombre;
    private String descripcion;
    private String zona;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;


}