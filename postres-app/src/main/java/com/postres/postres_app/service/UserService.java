package com.postres.postres_app.service;

import com.postres.postres_app.dto.LoginDTO;
import com.postres.postres_app.dto.UserDTO;
import com.postres.postres_app.entity.User;

public interface UserService {

    User registrar(UserDTO userDTO);
    User login(LoginDTO loginDTO);
    User findByCorreo(String correo);
}
