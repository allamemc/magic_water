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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.util.Calendar;
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
        if (aut == null) {
            mv.setViewName("login");
        } else {
            Usuario usuario = usuarioRepository.findById(aut.getName()).get();
            if (usuario.getPermiso().equals("supervisor")) {
                List<Tarea> tareas = tareaRepository.findAll();
                mv.addObject("tareas", tareas);
                mv.addObject("user", usuario);
            } else {
                List<Tarea> tareas = tareaRepository.findByUsuario(usuario);
                mv.addObject("tareas", tareas);
                mv.addObject("user", usuario);
                mv.setViewName("tareas");
            }

        }
        return mv;
    }

    @RequestMapping("/proyectos")
    public ModelAndView peticionProyectos(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if (aut == null) {
            mv.setViewName("login");
        } else {
            List<Proyecto> proyectos = proyectoRepository.findAll();
            Usuario usuario = usuarioRepository.findById(aut.getName()).get();
            mv.addObject("proyectos", proyectos);
            mv.addObject("user", usuario);
            mv.setViewName("proyectos");

        }
        return mv;
    }

    @RequestMapping("/addtarea")
    public ModelAndView peticionAddTarea(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = usuarioRepository.findById(aut.getName()).get();
        if (aut == null) {
            mv.setViewName("login");
        } else if (usuario.getPermiso().equals("supervisor")) {
            mv.setViewName("redirect:/tareas");
        } else {
            Tarea tarea = new Tarea();

            List<Proyecto> proyectos = proyectoRepository.findAll(); // Aquí recuperas todos los proyectos disponibles
            tarea.setUsuario(usuario);
            mv.addObject("tarea", tarea);
            mv.addObject("proyectos", proyectos); // Aquí añades los proyectos al modelo
            mv.addObject("user", usuario);
            mv.setViewName("addtarea");
        }
        return mv;
    }

    @RequestMapping("/addproyecto")
    public ModelAndView peticionAddProyecto(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = usuarioRepository.findById(aut.getName()).get();
        if (aut == null) {
            mv.setViewName("login");
        } else if (usuario.getPermiso().equals("supervisor")) {
            mv.setViewName("redirect:/proyectos");
        } else {
            Proyecto proyecto = new Proyecto();

            mv.addObject("proyecto", proyecto);
            mv.addObject("user", usuario);
            mv.setViewName("addproyecto");
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

    @RequestMapping(value = "/addproyecto", method = RequestMethod.POST)
    public ModelAndView peticionAddProyectoPost(@ModelAttribute Proyecto proyecto, Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if (aut == null) {
            mv.setViewName("login");
        } else {
            proyectoRepository.save(proyecto);
            String[] nombresTareas = {"Recolección de muestras", "Análisis de laboratorio", "Interpretación de resultados", "Elaboración del informe"};
            Usuario usuario = usuarioRepository.findById(aut.getName()).get();
            for (String nombreTarea : nombresTareas) {
                Tarea tarea = new Tarea();
                tarea.setTitulo(nombreTarea);
                tarea.setDescripcion("Pendiente de descripción");
                tarea.setUsuario(usuario);
                tarea.setEstado("Pendiente");
                tarea.setInicioprevisto(new java.sql.Date(proyecto.getFecha().getTime()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(proyecto.getFecha());
                calendar.add(Calendar.DAY_OF_MONTH, 30);
                Date fechaFinPrevista = new Date(calendar.getTimeInMillis());
                tarea.setFinprevisto(fechaFinPrevista);
                tarea.setProyecto(proyecto);
                tareaRepository.save(tarea);
            }
            mv.setViewName("redirect:/proyectos");
        }
        return mv;
    }

    @RequestMapping("/editartarea")
    public ModelAndView peticionEditarTarea(Authentication aut, @RequestParam("idtarea") Long idtarea) {
        ModelAndView mv = new ModelAndView();
        if (aut == null) {
            mv.setViewName("login");
        } else {
            Optional<Tarea> tarea = tareaRepository.findById(idtarea);
            mv.addObject("tarea", tarea.get());
            mv.setViewName("editartarea");
        }
        return mv;
    }

    @RequestMapping(value = "/editartarea", method = RequestMethod.POST)
    public ModelAndView peticionEditarTareaPost(@ModelAttribute Tarea tareaForm, Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if (aut == null) {
            mv.setViewName("login");
        } else {
            Optional<Tarea> tareaOptional = tareaRepository.findById((long) tareaForm.getIdtarea());
            if (tareaOptional.isPresent()) {
                Tarea tarea = tareaOptional.get();
                tarea.setTitulo(tareaForm.getTitulo());
                tarea.setDescripcion(tareaForm.getDescripcion());
                tarea.setEstado(tareaForm.getEstado());
                tarea.setInicioprevisto(tareaForm.getInicioprevisto());
                tarea.setFinprevisto(tareaForm.getFinprevisto());
                // Actualiza aquí cualquier otro campo que se envíe desde el formulario
                tareaRepository.save(tarea);
            }
            mv.setViewName("redirect:/tareas");
        }
        return mv;
    }

    @RequestMapping("/deletetarea")
    public ModelAndView peticionBorrarTarea(Authentication aut, @RequestParam("idtarea") Long idtarea) {
        ModelAndView mv = new ModelAndView();
        if (aut == null) {
            mv.setViewName("login");
        } else {
            tareaRepository.deleteById(idtarea);
            mv.setViewName("redirect:/tareas");
        }
        return mv;
    }


}