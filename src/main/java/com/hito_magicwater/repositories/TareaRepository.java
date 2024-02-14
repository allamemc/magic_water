package com.hito_magicwater.repositories;

import com.hito_magicwater.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
}