package com.postres.postres_app.controller;

import com.postres.postres_app.dto.PostreDTO;
import com.postres.postres_app.entity.Postre;
import com.postres.postres_app.entity.User;
import com.postres.postres_app.service.PostreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/postres")
public class PostreController {

    @Autowired
    private PostreService postreService;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("postre", new PostreDTO());
        return "nuevo-postre";
    }

    @PostMapping("/nuevo")
    public String guardarNuevo(@ModelAttribute("postre") PostreDTO dto,
                               Model model,
                               HttpSession session) {

        if (dto.getTitulo() == null || dto.getTitulo().isBlank() ||
                dto.getDescripcion() == null || dto.getDescripcion().isBlank() ||
                dto.getIngredientes() == null || dto.getIngredientes().isBlank()) {

            model.addAttribute("error", "Todos los campos son obligatorios.");
            return "nuevo-postre";
        }

        User usuario = (User) session.getAttribute("usuarioActual");
        postreService.crear(dto, usuario);

        return "redirect:/home";
    }

    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model, HttpSession session) {
        Postre postre = postreService.obtenerPorId(id);
        User usuario = (User) session.getAttribute("usuarioActual");

        boolean leGusta = postre.getUsuariosQueLesGusta().stream()
                .anyMatch(u -> u.getId().equals(usuario.getId()));

        model.addAttribute("postre", postre);
        model.addAttribute("usuario", usuario);
        model.addAttribute("leGusta", leGusta);
        model.addAttribute("likes", postre.getUsuariosQueLesGusta());

        return "detalle-postre";
    }

    @PostMapping("/like/{id}")
    public String darLike(@PathVariable Long id, HttpSession session) {
        User usuario = (User) session.getAttribute("usuarioActual");
        Postre postre = postreService.obtenerPorId(id);

        boolean yaLeDioLike = postre.getUsuariosQueLesGusta().stream()
                .anyMatch(u -> u.getId().equals(usuario.getId()));

        if (!yaLeDioLike) {
            postre.getUsuariosQueLesGusta().add(usuario);
            usuario.getPostresQueLeGustan().add(postre);
            postreService.guardar(postre); // o guarda directamente
        }

        return "redirect:/postres/detalle/" + id;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, HttpSession session) {
        Postre postre = postreService.obtenerPorId(id);
        User usuario = (User) session.getAttribute("usuarioActual");

        if (postre.getUsuario().getId().equals(usuario.getId())) {
            postreService.eliminar(id);
        }
        return "redirect:/home";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, HttpSession session) {
        Postre postre = postreService.obtenerPorId(id);
        User usuario = (User) session.getAttribute("usuarioActual");

        if (!postre.getUsuario().getId().equals(usuario.getId())) {
            return "redirect:/home";
        }

        PostreDTO dto = new PostreDTO();
        dto.setId(postre.getId());
        dto.setTitulo(postre.getTitulo());
        dto.setDescripcion(postre.getDescripcion());
        dto.setIngredientes(postre.getIngredientes());
        dto.setImagen(postre.getImagen());

        model.addAttribute("postre", dto);
        return "editar-postre";
    }

    @PostMapping("/editar/{id}")
    public String procesarEdicion(@PathVariable Long id,
                                  @ModelAttribute("postre") PostreDTO dto,
                                  Model model,
                                  HttpSession session) {

        if (dto.getTitulo() == null || dto.getTitulo().isBlank() ||
                dto.getDescripcion() == null || dto.getDescripcion().isBlank() ||
                dto.getIngredientes() == null || dto.getIngredientes().isBlank()) {

            model.addAttribute("error", "Todos los campos son obligatorios.");
            return "editar-postre";
        }

        User usuario = (User) session.getAttribute("usuarioActual");
        Postre postreOriginal = postreService.obtenerPorId(id);

        if (!postreOriginal.getUsuario().getId().equals(usuario.getId())) {
            return "redirect:/home";
        }

        postreService.actualizar(id, dto);
        return "redirect:/home";
    }

    @GetMapping("/mis-postres")
    public String verMisPostres(Model model, HttpSession session) {
        User usuario = (User) session.getAttribute("usuarioActual");
        List<Postre> misPostres = postreService.buscarPorUsuario(usuario);

        model.addAttribute("postres", misPostres);
        model.addAttribute("usuario", usuario);

        return "mis-postres";
    }


}
