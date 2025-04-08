package com.postres.postres_app.service.impl;

import com.postres.postres_app.dto.LoginDTO;
import com.postres.postres_app.dto.UserDTO;
import com.postres.postres_app.entity.User;
import com.postres.postres_app.repository.UserRepository;
import com.postres.postres_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registrar(UserDTO dto) {
        User user = new User();
        user.setNombre(dto.getNombre());
        user.setCorreo(dto.getCorreo());
        user.setPassword(dto.getPassword()); // Guardado directo sin codificación
        return userRepository.save(user);
    }

    @Override
    public User login(LoginDTO dto) {
        Optional<User> userOpt = userRepository.findByCorreo(dto.getCorreo());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Comparación directa sin BCrypt
            if (dto.getPassword().equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByCorreo(String correo) {
        return userRepository.findByCorreo(correo).orElse(null);
    }
}
