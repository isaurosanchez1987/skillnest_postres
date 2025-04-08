package com.postres.postres_app.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // Permitir acceso sin sesión a estas rutas
        if (uri.startsWith("/login") || uri.startsWith("/registro") || uri.startsWith("/css") || uri.startsWith("/js") || uri.contains("/resources")) {
            return true;
        }

        // Si hay usuario en sesión, permitir acceso
        if (session != null && session.getAttribute("usuarioActual") != null) {
            return true;
        }

        // Redirigir si no hay sesión
        response.sendRedirect("/login");
        return false;
    }
}
