package com.postres.postres_app.service.impl;


import com.postres.postres_app.dto.PostreDTO;
import com.postres.postres_app.entity.Postre;
import com.postres.postres_app.entity.User;
import com.postres.postres_app.repository.PostreRepository;
import com.postres.postres_app.service.PostreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostreServiceImpl  implements PostreService {

    @Autowired
    private PostreRepository postreRepository;

    @Override
    public Postre crear(PostreDTO dto, User usuario) {
        Postre postre = new Postre();
        postre.setTitulo(dto.getTitulo());
        postre.setDescripcion(dto.getDescripcion());
        postre.setIngredientes(dto.getIngredientes());
        postre.setImagen(dto.getImagen());
        postre.setUsuario(usuario);
        return postreRepository.save(postre);
    }

    @Override
    public Postre actualizar(Long id, PostreDTO dto) {
        Postre postre = postreRepository.findById(id).orElseThrow();
        postre.setTitulo(dto.getTitulo());
        postre.setDescripcion(dto.getDescripcion());
        postre.setIngredientes(dto.getIngredientes());
        postre.setImagen(dto.getImagen());
        return postreRepository.save(postre);
    }

    @Override
    public Postre guardar(Postre postre) {
        return postreRepository.save(postre);
    }

    @Override
    public void eliminar(Long id) {
        postreRepository.deleteById(id);
    }

    @Override
    public List<Postre> listarTodos() {
        return postreRepository.findAll()
                .stream()
                .sorted((a, b) -> a.getTitulo().compareToIgnoreCase(b.getTitulo()))
                .toList();
    }

    @Override
    public List<Postre> buscarPorUsuario(User usuario) {
        return postreRepository.findByUsuario(usuario);
    }

    @Override
    public List<Postre> buscarPorTitulo(String titulo) {
        return postreRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public Postre obtenerPorId(Long id) {
        return postreRepository.findById(id).orElse(null);
    }
}
