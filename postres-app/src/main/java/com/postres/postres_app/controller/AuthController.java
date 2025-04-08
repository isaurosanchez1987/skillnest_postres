package com.postres.postres_app.controller;


import com.postres.postres_app.dto.LoginDTO;
import com.postres.postres_app.dto.UserDTO;
import com.postres.postres_app.entity.User;
import com.postres.postres_app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new UserDTO());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute("usuario") UserDTO dto, Model model, HttpSession session) {

        if (dto.getNombre().isBlank() || dto.getCorreo().isBlank() || dto.getPassword().isBlank()) {
            model.addAttribute("error", "Todos los campos son obligatorios.");
            return "registro";
        }
        if (!dto.getPassword().equals(dto.getConfirmarPassword())) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
            return "registro";
        }

        User usuario = userService.registrar(dto);
        session.setAttribute("usuarioActual", usuario);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("login", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login") LoginDTO dto, Model model, HttpSession session) {
        User usuario = userService.login(dto);
        if (usuario == null) {
            model.addAttribute("error", "Credenciales inválidas.");
            return "login";
        }
        session.setAttribute("usuarioActual", usuario);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
