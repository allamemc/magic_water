package com.hito_magicwater.repositories;

import com.hito_magicwater.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}