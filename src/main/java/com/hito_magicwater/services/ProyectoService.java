package com.hito_magicwater.services;

import com.hito_magicwater.models.Proyecto;
import com.hito_magicwater.models.Usuario;
import com.hito_magicwater.repositories.ProyectoRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;


    @PostConstruct
    public void crearProyectos() {
        Proyecto proyecto1 = new Proyecto();
        proyecto1.setIdproyecto(1);
        proyecto1.setNombre("Proyecto de purificación de agua");
        proyecto1.setDescripcion("Este proyecto se centra en la purificación de agua utilizando técnicas avanzadas de filtración y destilación.");
        proyecto1.setZona("Zona Norte");
        proyecto1.setFecha("2023-01-01");
        proyectoRepository.save(proyecto1);

        Proyecto proyecto2 = new Proyecto();
        proyecto2.setIdproyecto(2);
        proyecto2.setNombre("Proyecto de desalinización de agua");
        proyecto2.setDescripcion("Este proyecto tiene como objetivo desarrollar métodos eficientes para la desalinización del agua del mar.");
        proyecto2.setZona("Zona Sur");
        proyecto2.setFecha("2023-06-01");
        proyectoRepository.save(proyecto2);

        Proyecto proyecto3 = new Proyecto();
        proyecto3.setIdproyecto(3);
        proyecto3.setNombre("Proyecto de reutilización de agua");
        proyecto3.setDescripcion("Este proyecto se centra en la reutilización del agua para reducir el consumo de agua y promover la sostenibilidad.");
        proyecto3.setZona("Zona Este");
        proyecto3.setFecha("2023-11-01");
        proyectoRepository.save(proyecto3);
    }
}