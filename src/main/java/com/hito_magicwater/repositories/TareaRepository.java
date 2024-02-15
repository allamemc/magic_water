package com.hito_magicwater.repositories;

import com.hito_magicwater.models.Proyecto;
import com.hito_magicwater.models.Tarea;
import com.hito_magicwater.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByUsuario(Usuario usuario);
    List<Tarea> findByProyecto(Proyecto proyecto);
}