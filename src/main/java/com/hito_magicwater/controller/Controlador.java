package com.hito_magicwater.controller;

import com.hito_magicwater.models.*;
import com.hito_magicwater.repositories.ProyectoRepository;
import com.hito_magicwater.repositories.TareaRepository;
import com.hito_magicwater.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

@Controller
public class Controlador {
    @Autowired
    private TareaRepository tareaRepository;
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Representa al usuario que ha iniciado sesión.
    private Usuario login;

    @RequestMapping("/")
    public ModelAndView peticionRaiz(Authentication aut) {
        ModelAndView mv = new ModelAndView();

        if (aut == null) {
            mv.setViewName("login");
        } else {
            login = usuarioRepository.findById(aut.getName()).get();
            mv.addObject("user", login);
            mv.setViewName("index");
        }

        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView peticionLogin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/tareas")
    public ModelAndView peticionTareas(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        List<Tarea> tareas = tareaRepository.findAll();
        mv.addObject("tareas", tareas);
        mv.setViewName("tareas");

        if (aut == null) {
            mv.addObject("user", null);
        } else {
            mv.addObject("user", login);
        }

        return mv;
    }

    @RequestMapping("/addtarea")
    public ModelAndView peticionAddTarea(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if (aut == null) {
            mv.setViewName("login");
        } else {
            Tarea tarea = new Tarea();
            Usuario usuario = usuarioRepository.findById(aut.getName()).get();
            List<Proyecto> proyectos = proyectoRepository.findAll(); // Aquí recuperas todos los proyectos disponibles
            tarea.setUsuario(usuario);
            mv.addObject("tarea", tarea);
            mv.addObject("proyectos", proyectos); // Aquí añades los proyectos al modelo
            mv.addObject("user", usuario);
            mv.setViewName("addtarea");
        }
        return mv;
    }

    @RequestMapping(value = "/addtarea", method = RequestMethod.POST)
    public ModelAndView peticionAddTareaPost(@ModelAttribute Tarea tarea, Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if (aut == null) {
            mv.setViewName("login");
        } else {
            tareaRepository.save(tarea);
            mv.setViewName("redirect:/tareas");
        }
        return mv;
    }

    @RequestMapping("/proyectos")
    public ModelAndView peticionProyectos() {
        ModelAndView mv = new ModelAndView();
        List<Proyecto> proyectos = proyectoRepository.findAll();
        mv.addObject("proyectos", proyectos);
        mv.setViewName("proyectos");
        return mv;
    }


}