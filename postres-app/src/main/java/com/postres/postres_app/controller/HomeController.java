package com.postres.postres_app.controller;

import com.postres.postres_app.entity.Postre;
import com.postres.postres_app.entity.User;
import com.postres.postres_app.service.PostreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostreService postreService;

    @GetMapping("/home")
    public String mostrarHome(Model model, HttpSession session,
                              @RequestParam(value = "filtro", required = false) String filtro) {

        List<Postre> postres;

        if (filtro != null && !filtro.isBlank()) {
            postres = postreService.buscarPorTitulo(filtro);
            model.addAttribute("filtro", filtro);
        } else {
            postres = postreService.listarTodos();
        }

        User usuario = (User) session.getAttribute("usuarioActual");

        model.addAttribute("usuario", usuario);
        model.addAttribute("postres", postres);

        return "home";
    }
}
