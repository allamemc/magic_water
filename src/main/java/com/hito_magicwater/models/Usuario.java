package com.hito_magicwater.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id
    private String NIF;
    private String categoria;
    private String nombre;
    private String apellidos;
    private String email;
    private String tlf;
    private String password;
    private boolean activo;
    private String permiso;

}
