package com.hito_magicwater.services;

import com.hito_magicwater.models.Usuario;
import com.hito_magicwater.repositories.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void insertUsuarios() {
        Usuario usuario1 = new Usuario();
        usuario1.setNIF("12345678A");
        usuario1.setNombre("Juan");
        usuario1.setApellidos("Perez");
        usuario1.setEmail("juan.perez@example.com");
        usuario1.setTlf("123456789");
        usuario1.setPassword(passwordEncoder.encode("123"));
        usuario1.setActivo(true);
        usuario1.setPermiso("trabajador");
        usuario1.setCategoria("Ingeniero");
        usuarioRepository.save(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setNIF("23456789B");
        usuario2.setNombre("Maria");
        usuario2.setApellidos("Gomez");
        usuario2.setEmail("maria.gomez@example.com");
        usuario2.setTlf("234567890");
        usuario2.setPassword(passwordEncoder.encode("123"));
        usuario2.setActivo(true);
        usuario2.setPermiso("trabajador");
        usuario2.setCategoria("Analista");
        usuarioRepository.save(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setNIF("34567890C");
        usuario3.setNombre("Carlos");
        usuario3.setApellidos("Rodriguez");
        usuario3.setEmail("carlos.rodriguez@example.com");
        usuario3.setTlf("345678901");
        usuario3.setPassword(passwordEncoder.encode("123"));
        usuario3.setActivo(true);
        usuario3.setPermiso("trabajador");
        usuario3.setCategoria("Ingeniero");
        usuarioRepository.save(usuario3);

        Usuario usuario4 = new Usuario();
        usuario4.setNIF("45678901D");
        usuario4.setNombre("Ana");
        usuario4.setApellidos("Martinez");
        usuario4.setEmail("ana.martinez@example.com");
        usuario4.setTlf("456789012");
        usuario4.setPassword(passwordEncoder.encode("123"));
        usuario4.setActivo(true);
        usuario4.setPermiso("trabajador");
        usuario4.setCategoria("Analista");
        usuarioRepository.save(usuario4);

        Usuario usuario5 = new Usuario();
        usuario5.setNIF("56789012E");
        usuario5.setNombre("Pedro");
        usuario5.setApellidos("Gonzalez");
        usuario5.setEmail("pedro.gonzalez@example.com");
        usuario5.setTlf("567890123");
        usuario5.setPassword(passwordEncoder.encode("123"));
        usuario5.setActivo(true);
        usuario5.setPermiso("supervisor");
        usuario5.setCategoria("Ingeniero");
        usuarioRepository.save(usuario5);
    }
}