package com.postres.postres_app.service;

import com.postres.postres_app.dto.PostreDTO;
import com.postres.postres_app.entity.Postre;
import com.postres.postres_app.entity.User;

import java.util.List;

public interface PostreService {

    Postre crear(PostreDTO dto, User usuario);
    Postre actualizar(Long id, PostreDTO dto);
    Postre guardar(Postre postre);
    void eliminar(Long id);
    List<Postre> listarTodos();
    List<Postre> buscarPorUsuario(User usuario);
    List<Postre> buscarPorTitulo(String titulo);
    Postre obtenerPorId(Long id);
}
